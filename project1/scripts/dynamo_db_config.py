import boto3
from botocore.exceptions import ClientError

endpoint_url = "http://localhost:4566"
aws_access_key_id = "test"
aws_secret_access_key = "test"
region_name = "sa-east-1"

# Configure the DynamoDB client to connect to LocalStack
dynamodb = boto3.resource(
    "dynamodb",
    endpoint_url=endpoint_url,
    aws_access_key_id=aws_access_key_id,
    aws_secret_access_key=aws_access_key_id,
    region_name=region_name,
)


def create_users_table():
    print("Creating table")
    try:
        table = dynamodb.create_table(
            TableName="Users",
            KeySchema=[
                {
                    "AttributeName": "id",  # Primary key
                    "KeyType": "HASH",  # Partition key
                }
            ],
            AttributeDefinitions=[
                {"AttributeName": "id", "AttributeType": "S"},  # String type for id
            ],
            ProvisionedThroughput={"ReadCapacityUnits": 5, "WriteCapacityUnits": 5},
        )

        # Wait until the table is created
        table.wait_until_exists()
        print(f"Table '{table.table_name}' created successfully.")

    except ClientError as e:
        print(f"Error creating table: {e.response['Error']['Message']}")


if __name__ == "__main__":
    create_users_table()
