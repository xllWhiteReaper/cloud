resource "aws_instance" "be-server" {
  ami           = "ami-34c14f4a" # Use a dummy AMI ID
  instance_type = "t2.micro"
  count         = 1

  tags = {
    Name = "be-server-${count.index}"
  }
}


resource "aws_s3_bucket" "file_uploads_bucket" {
  bucket = "file-uploads"  
    tags = {
    Name = "MyAuthenticatedFilesBucket"
  }
}

resource "aws_iam_user" "s3_user" {
  name = "s3_user"
  tags = {
    Name = "AdminIAMUser"
  }
}

resource "aws_iam_access_key" "s3_user_key" {
  user = aws_iam_user.s3_user.name 
}

resource "aws_s3_bucket_policy" "my_bucket_policy" {
  bucket = aws_s3_bucket.file_uploads_bucket.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Principal = {
          AWS = aws_iam_user.s3_user.arn
        }
        Action = [
          "s3:GetObject",
          "s3:PutObject"
        ]
        Resource = "${aws_s3_bucket.file_uploads_bucket.arn}/*"
      },
    ]
  })
}
