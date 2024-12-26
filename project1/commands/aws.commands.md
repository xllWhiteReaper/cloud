# Important AWS Commands

## **List Instances**:

```bash
aws ec2 describe-instances --endpoint-url http://localhost:4566
```

## **List Available AMIs**:

```bash
aws ec2 describe-images --owners self --endpoint-url http://localhost:4566
```

## **Terminate an Instance**:

```bash
aws ec2 terminate-instances --instance-ids <your-instance-id> --endpoint-url http://localhost:4566
```

## **List Available S3 buckets**:

```bash
aws s3api list-buckets --endpoint-url http://localhost:4566
```
