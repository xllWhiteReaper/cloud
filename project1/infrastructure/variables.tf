variable "my_aws_endpoint" {
  description = "The endpoint to test locally using LocalStack"
  type        = string
  default     = "http://localhost:4566"
}

variable "is_using_credentials" {
  description = "This will tell terraform to use or not the credentials, and since we are on dev mode and are using LocalStack, we can set this to false"
  type = bool
  default = false
}