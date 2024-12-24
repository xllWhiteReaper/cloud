provider "aws" {
  region                      = "us-west-2"
  access_key                  = "test"
  secret_key                  = "test"
  endpoint                    = "http://localhost:4566"
  skip_credentials_validation  = true
  skip_get_ec2_platforms      = true
}
