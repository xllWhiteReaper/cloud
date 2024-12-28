# Important AWS S3 Commands

## **List Available S3 buckets**:

```bash
aws s3api list-buckets --endpoint-url http://localhost:4566
```

## **List Objects in the Bucket**:

```bash
aws --profile localstack --endpoint-url=http://localhost:4566 s3 ls s3://your-bucket-name
```

## **Put s3 Objects in the Bucket**:

```bash
aws --profile localstack --endpoint-url=http://localhost:4566 s3 cp /path/to/your/folder s3://my-bucket/ --recursive
```

## **Empty s3 Bucket**:

```bash
aws --profile localstack --endpoint-url=http://localhost:4566 s3 rm s3://your-bucket-name/ --recursive
```
