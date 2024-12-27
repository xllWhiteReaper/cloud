# Important AWS S3 Commands

## **List Available S3 buckets**:

```bash
aws s3api list-buckets --endpoint-url http://localhost:4566
```

## **List Objects in the Bucket**:

```bash
aws --profile localstack --endpoint-url=http://localhost:4566 s3 ls s3://your-bucket-name
```
