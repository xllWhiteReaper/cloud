output "access_key_id" {
  value     = aws_iam_access_key.s3_user_key.id
  sensitive = true  # Mark it as sensitive to prevent it from being displayed in logs
}

output "secret_access_key" {
  value     = aws_iam_access_key.s3_user_key.secret
  sensitive = true  # Mark it as sensitive
}