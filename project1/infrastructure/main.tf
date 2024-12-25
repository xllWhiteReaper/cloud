resource "aws_instance" "be-server" {
  ami           = "ami-34c14f4a" # Use a dummy AMI ID
  instance_type = "t2.micro"
  count         = 1

  tags = {
    Name = "be-server-${count.index}"
  }
}