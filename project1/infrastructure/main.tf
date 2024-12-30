resource "aws_instance" "be-server" {
  ami           = "ami-34c14f4a" # Use a dummy AMI ID
  instance_type = "t2.micro"
  count         = 1

  tags = {
    Name = "be-server-${count.index}"
  }
}

# This will work for the backend 
resource "aws_s3_bucket" "file_uploads_bucket" {
  bucket = "cms-be"
  tags = {
    Name = "MyAuthenticatedBEBucket"
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


# This will be for the frontend
resource "aws_s3_bucket" "app_host_bucket" {
  bucket = "cms-fe"
  tags = {
    Name = "MyHostBucket"
  }
}

resource "aws_s3_bucket_acl" "public_bucket_acl" {
  bucket = aws_s3_bucket.app_host_bucket.id
  acl    = "public-read"
}

resource "aws_s3_bucket_policy" "public_bucket_policy" {
  bucket = aws_s3_bucket.app_host_bucket.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect    = "Allow"
        Principal = "*"
        Action    = "s3:GetObject"
        Resource  = "${aws_s3_bucket.app_host_bucket.arn}/*"
      }
    ]
  })
}


## Other modules
module "db" {
  source = "./modules/db"
}

output "dynamo_access_key_id" {
  value     = module.db.dynamo_access_key_id
  sensitive = true
}

output "dynamo_secret_access_key" {
  value     = module.db.dynamo_secret_access_key
  sensitive = true
}
