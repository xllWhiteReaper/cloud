terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"  # Adjust version as needed
    }
  }
}

provider "aws" {
  skip_credentials_validation = !var.is_using_credentials
  skip_metadata_api_check = !var.is_using_credentials
  skip_requesting_account_id = !var.is_using_credentials
  skip_region_validation = !var.is_using_credentials
  region     = "sa-east-1"
  access_key = "test"
  secret_key = "test"
  endpoints {
    ec2 = var.my_aws_endpoint
    s3  = var.my_aws_endpoint
  }
}
