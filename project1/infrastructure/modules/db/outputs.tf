output "dynamo_access_key_id" {
  value     = aws_iam_access_key.dynamodb_user_key.id
  sensitive = true # Mark it as sensitive to prevent it from being displayed in logs
}

output "dynamo_secret_access_key" {
  value     = aws_iam_access_key.dynamodb_user_key.secret
  sensitive = true # Mark it as sensitive
}
