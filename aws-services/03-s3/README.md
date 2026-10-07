# S3: Simple Storage Service (Storage)

## What is S3?

Amazon S3 is an object storage service that offers virtually unlimited, highly durable (99.999999999%, "11 nines") and highly available storage accessible over HTTP/HTTPS. It is used for files of any type, from a few bytes up to 5 TB per object.

## Buckets

A bucket is a container for objects.

- The bucket name is **globally unique** across all AWS accounts.
- A bucket is created in a specific **region**.
- Names are 3-63 characters, lowercase letters, numbers and hyphens.
- Access is blocked from the public by default (Block Public Access).

## Objects

An object is the stored data and consists of:

- **Key**: the unique name/path in the bucket (e.g. `images/logo.png`).
- **Value**: the data itself.
- **Metadata**: system and user-defined key-value pairs.
- **Version ID**: if versioning is enabled.

S3 has a flat structure; "folders" are just key prefixes.

## Storage Classes

| Class | Use |
|---|---|
| S3 Standard | Frequently accessed data |
| S3 Intelligent-Tiering | Unknown or changing access patterns; moves data automatically |
| S3 Standard-IA | Infrequent access, rapid retrieval |
| S3 One Zone-IA | Infrequent access, stored in a single AZ (cheaper) |
| S3 Glacier Instant Retrieval | Archive with millisecond access |
| S3 Glacier Flexible Retrieval | Archive, retrieval in minutes to hours |
| S3 Glacier Deep Archive | Lowest cost, long-term archive (retrieval in hours) |

## Versioning

Versioning keeps multiple versions of an object in the same bucket. Overwriting creates a new version and deleting adds a *delete marker*, so earlier versions can be restored. It protects against accidental deletion or overwrite. Once enabled it can only be suspended, not removed.

## Lifecycle Policies

Lifecycle rules automate transitions and expiration of objects, for example:

- Move to Standard-IA after 30 days.
- Move to Glacier after 90 days.
- Delete (expire) after 365 days.
- Delete old noncurrent versions or incomplete multipart uploads.

This reduces storage cost without manual work.

## Encryption

- **In transit**: HTTPS/TLS.
- **At rest** (all new objects are encrypted by default):
  - **SSE-S3**: keys managed by S3 (AES-256).
  - **SSE-KMS**: keys managed in AWS KMS, with audit trail and access control.
  - **SSE-C**: customer-provided keys.
  - **Client-side encryption**: data encrypted before upload.

## Bucket Policies

A bucket policy is a resource-based JSON policy attached to a bucket to control access for users, accounts or the public.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Sid": "AllowReadFromAccount",
      "Effect": "Allow",
      "Principal": { "AWS": "arn:aws:iam::123456789012:root" },
      "Action": "s3:GetObject",
      "Resource": "arn:aws:s3:::my-bucket/*"
    }
  ]
}
```

Access is also controlled with IAM policies, ACLs (legacy) and Block Public Access settings.

## Common Use Cases

- Backup and disaster recovery.
- Static website hosting.
- Data lake and analytics storage.
- Storing application assets, media and user uploads.
- Log storage (CloudTrail, ALB, CloudFront logs).
- Archiving with Glacier.
- Storing Terraform remote state.
