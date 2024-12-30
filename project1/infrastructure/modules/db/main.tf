resource "aws_dynamodb_table" "users" {
  name         = var.db_name
  billing_mode = "PAY_PER_REQUEST"

  attribute {
    name = "id"
    type = "S"
  }

  hash_key = "id"

  tags = {
    Name = "UsersTable"
  }
}

resource "aws_iam_user" "dynamodb_user" {
  name = "DynamoDBAdmin"
}

resource "aws_iam_access_key" "dynamodb_user_key" {
  user = aws_iam_user.dynamodb_user.name
}

resource "aws_iam_user_policy" "dynamodb_policy" {
  name = "DynamoDBAdminPolicy"
  user = aws_iam_user.dynamodb_user.name

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = [
          "dynamodb:*"
        ]
        Resource = "*"
      }
    ]
  })
}
