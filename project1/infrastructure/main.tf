resource "aws_instance" "be-server" {
  ami           = "ami-34c14f4a" # Use a dummy AMI ID
  instance_type = "t2.micro"
  count         = 1

  tags = {
    Name = "be-server-${count.index}"
  }
}

resource "aws_s3_bucket" "my-bucket" {
  bucket = "spring-file"
  aws_s3_bucket_acl = false

  versioning {
    enabled = true
  }

  server_side_encryption_configuration {
    rule {
      apply_server_side_encryption_by_default {
        sse_algorithm = "AES256"
      }
    }
  }
}