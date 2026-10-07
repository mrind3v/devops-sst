# IAM: Identity and Access Management (Governance)

## What is IAM?

AWS Identity and Access Management (IAM) is the service that controls **who** can access AWS (authentication) and **what** they can do (authorization). It is global (not tied to a region) and free to use. Every AWS account starts with a **root user** with unrestricted access, which should only be used for initial setup.

## Core Components

### Users

An IAM user represents a person or application that interacts with AWS. A user has long-term credentials: a console password and/or access keys (access key ID and secret access key) for the CLI and SDKs.

### Groups

A group is a collection of IAM users. Permissions attached to the group apply to every member, so access is managed per job function (for example `Developers`, `Admins`) instead of per user. A group cannot contain other groups.

### Roles

A role is an identity with permissions that is **assumed** temporarily instead of being tied to one person. Credentials are short-lived and issued by AWS STS. Roles are used by:

- AWS services (an EC2 instance reading from S3)
- Users or accounts that need cross-account access
- Federated identities (SSO, external identity providers)

### Policies

A policy is a JSON document that defines permissions. Types:

| Type | Description |
|---|---|
| AWS managed | Created and maintained by AWS (e.g. `AmazonS3ReadOnlyAccess`) |
| Customer managed | Created by you, reusable across identities |
| Inline | Embedded directly in a single user, group or role |
| Resource-based | Attached to a resource (e.g. S3 bucket policy) |

Example policy:

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": ["s3:GetObject", "s3:ListBucket"],
      "Resource": [
        "arn:aws:s3:::my-bucket",
        "arn:aws:s3:::my-bucket/*"
      ]
    }
  ]
}
```

Each statement has an `Effect` (Allow/Deny), `Action` (API calls), `Resource` (what it applies to) and optionally a `Condition`.

### Permissions

Permissions are the allow/deny rules that result from policies. Evaluation rules:

1. Everything is **denied by default** (implicit deny).
2. An explicit **Allow** in a policy grants access.
3. An explicit **Deny** always overrides any Allow.

## Least Privilege

Grant only the permissions required to perform a task, and nothing more. Start with minimal permissions and add more as needed. Scope `Action` and `Resource` as narrowly as possible, use conditions where useful, and review unused permissions with IAM Access Analyzer and "last accessed" data.

## IAM Best Practices

- Do not use the root user for daily work; secure it with MFA and delete its access keys.
- Enable MFA for all users, especially privileged ones.
- Follow least privilege.
- Assign permissions through groups, not directly to users.
- Use roles instead of long-term access keys for applications and AWS services.
- Rotate credentials regularly and remove unused users and keys.
- Enforce a strong password policy.
- Use customer managed policies over inline policies for reuse.
- Monitor activity with AWS CloudTrail.
- Use IAM Identity Center (SSO) for workforce access.

## Common Use Cases

- Giving developers read-only access to production and full access to dev.
- Letting an EC2 instance or Lambda function access S3 or DynamoDB through a role.
- Cross-account access between separate AWS accounts.
- Granting CI/CD pipelines (e.g. GitHub Actions) permission to deploy.
- Enforcing MFA and auditing who did what in an account.
