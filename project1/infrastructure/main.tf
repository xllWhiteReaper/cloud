# resource "aws_instance" "be-server" {
#   ami           = "ami-34c14f4a" # Use a dummy AMI ID
#   instance_type = "t2.micro"
#   count         = 1

#   tags = {
#     Name = "be-server-${count.index}"
#   }
# }

# # resource "aws_s3_bucket" "my_bucket" {
# #   bucket = "my-authenticated-bucket"

# #   tags = {
# #     Name = "MyAuthenticatedBucket"
# #   }
# # }

# resource "aws_s3_bucket" "my_bucket" {
#   bucket = "publicbuck" # Update the bucket name
# }

# resource "aws_s3_bucket_acl" "example" {
#   bucket = aws_s3_bucket.my_bucket.id
#   acl    = "public-read-write"
# }



# # resource "aws_iam_user" "s3_user" {
# #   name = "s3_user"
# # }

# # resource "aws_iam_access_key" "s3_user_key" {
# #   user = aws_iam_user.s3_user.name 
# # }

# # resource "aws_s3_bucket_policy" "my_bucket_policy" {
# #   bucket = aws_s3_bucket.my_bucket.id

# #   policy = jsonencode({
# #     Version = "2012-10-17"
# #     Statement = [
# #       {
# #         Effect = "Allow"
# #         Principal = {
# #           AWS = aws_iam_user.s3_user.arn
# #         }
# #         Action = [
# #           "s3:GetObject",
# #           "s3:PutObject"
# #         ]
# #         Resource = "${aws_s3_bucket.my_bucket.arn}/*"
# #       },
# #     ]
# #   })
# # }

# terraform {
#   backend "s3" {
#     bucket         = "my-backend"  # Choose a unique bucket name
#     key            = "terraform.tfstate"          # Path to the state file
#     region         = "us-east-1"                   # You can specify the region
#     use_path_style = true        
#     skip_credentials_validation = true                   # Important for LocalStack
#     skip_requesting_account_id = true
#     skip_metadata_api_check = true
#     skip_region_validation = true
#     skip_s3_checksum = true
#   }
# }

# provider "aws" {
#   region     = "us-east-1"
#   access_key = "test"
#   secret_key = "test"
#   skip_credentials_validation = true
#   skip_metadata_api_check     = true
#   skip_requesting_account_id  = true
#   skip_region_validation      = true
#   s3_use_path_style = true
  
#   endpoints {
#     s3 = "http://localhost:4566"
#   }
# }

resource "aws_s3_bucket" "file_uploads_bucket" {
  bucket = "file-uploads"  
}