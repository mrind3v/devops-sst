# DynamoDB & RDS: Database Services

## Amazon DynamoDB

### What is DynamoDB?

DynamoDB is a fully managed, serverless **NoSQL** key-value and document database that delivers single-digit millisecond performance at any scale. There are no servers to manage; AWS handles scaling, replication and backups.

### NoSQL

NoSQL databases do not use fixed relational schemas or joins. DynamoDB stores flexible, schema-less items and scales horizontally, which suits high-volume, low-latency workloads with simple access patterns.

### Tables

A table is a collection of items. When creating a table you define only the primary key; other attributes are flexible.

### Items

An item is a single record in a table (similar to a row). Items in the same table can have different attributes. Maximum item size is 400 KB.

### Attributes

An attribute is a single data element of an item (similar to a column), such as `name` or `email`. Types include string, number, binary, boolean, list, map and set.

### Partition Key

The partition key (hash key) is the primary key attribute whose value is hashed to decide the physical partition where the item is stored. It must be chosen to spread data and traffic evenly. A table with only a partition key requires each key value to be unique.

### Sort Key

The sort key (range key) is an optional second part of the primary key. Items with the same partition key are stored together and ordered by the sort key, which enables range queries (`begins_with`, `between`).

Example (`Orders` table):

| CustomerId (partition key) | OrderDate (sort key) | Total |
|---|---|---|
| C001 | 2026-01-10 | 250 |
| C001 | 2026-02-05 | 120 |
| C002 | 2026-01-22 | 90 |

### Use Cases

- User profiles and session data.
- Shopping carts and gaming leaderboards.
- IoT and time-series data.
- Serverless backends with Lambda and API Gateway.
- Terraform state locking.

---

## Amazon RDS

### Relational Database

A relational database stores data in tables with rows and columns, enforces a schema, and uses SQL with joins and ACID transactions. Amazon RDS (Relational Database Service) is a managed service that sets up, operates and scales relational databases, handling patching, backups and failover.

### Supported Engines

- Amazon Aurora (MySQL and PostgreSQL compatible)
- MySQL
- PostgreSQL
- MariaDB
- Oracle
- Microsoft SQL Server

### DB Instances

A DB instance is the isolated database environment running in the cloud. You choose the engine, instance class (CPU/memory, e.g. `db.t3.micro`), storage type and size (gp3, io1), and place it in a VPC subnet group. You do not get OS access.

### Security

- Deploy in **private subnets** of a VPC.
- Control access with **security groups** (e.g. allow port 3306/5432 only from the application).
- **Encryption at rest** with KMS and **in transit** with SSL/TLS.
- **IAM** for managing the service and optional IAM database authentication.
- Store credentials in AWS Secrets Manager.

### Backups

- **Automated backups**: daily snapshot plus transaction logs, enabling point-in-time recovery within the retention period (1-35 days).
- **Manual snapshots**: user-initiated and kept until deleted.
- Restoring always creates a new DB instance.

### Multi-AZ

Multi-AZ keeps a synchronous standby copy in a different Availability Zone. If the primary fails, RDS automatically fails over to the standby with the same endpoint. It provides **high availability** and is not used for read scaling.

### Read Replicas

Read replicas are asynchronous copies of the primary used to scale **read** traffic and offload reporting queries. They can be in the same region or a different one, have their own endpoint, and can be promoted to a standalone database.

| | Multi-AZ | Read replica |
|---|---|---|
| Purpose | High availability, failover | Read scaling |
| Replication | Synchronous | Asynchronous |
| Readable | No (standby) | Yes |

### Use Cases

- Web and mobile application backends.
- E-commerce, ERP and CRM systems needing transactions and joins.
- Reporting and analytics on structured data.
- Migrating existing on-premises relational databases.

---

## DynamoDB vs RDS

| | DynamoDB | RDS |
|---|---|---|
| Type | NoSQL (key-value/document) | Relational (SQL) |
| Schema | Flexible | Fixed |
| Scaling | Horizontal, automatic | Vertical, plus read replicas |
| Management | Serverless | Managed instances |
| Best for | Simple access patterns at massive scale | Complex queries, joins, transactions |
