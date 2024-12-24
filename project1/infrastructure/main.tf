resource "aws_instance" "example" {
  ami           = "ami-12345678" # Use a dummy AMI ID
  instance_type = "t2.micro"

  tags = {
    Name = "be-app"
  }
}