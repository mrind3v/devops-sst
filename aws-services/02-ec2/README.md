# EC2: Elastic Compute Cloud (Compute)

## What is EC2?

Amazon EC2 provides resizable virtual servers (instances) in the cloud. You choose the operating system, CPU, memory, storage and networking, and pay only for the time you use (per second for Linux). It is the basic compute building block on AWS.

## AMI (Amazon Machine Image)

An AMI is a template used to launch an instance. It contains the operating system, pre-installed software and configuration, plus the root volume snapshot. AMIs can be AWS-provided (Amazon Linux, Ubuntu), from the Marketplace, or custom images you create from your own instances. AMIs are regional.

## Instance Types

An instance type defines the CPU, memory, storage and network capacity. Naming: `t3.micro` = family `t`, generation `3`, size `micro`.

| Family | Optimized for | Examples |
|---|---|---|
| General purpose | Balanced workloads | `t3`, `m5` |
| Compute optimized | CPU-heavy tasks | `c5` |
| Memory optimized | Large in-memory workloads, databases | `r5`, `x1` |
| Storage optimized | High disk I/O | `i3`, `d2` |
| Accelerated computing | GPU, ML | `p3`, `g4` |

## Key Pairs

A key pair (public key + private key) is used to securely log in to an instance. AWS stores the public key; you download the private key (`.pem`) once. Linux instances use it for SSH:

```bash
ssh -i mykey.pem ec2-user@<public-ip>
```

## Security Groups

A security group is a virtual, **stateful** firewall at the instance level.

- Rules are **allow only** (no deny rules).
- Inbound rules control incoming traffic, outbound rules control outgoing traffic.
- Stateful: return traffic for an allowed request is automatically allowed.
- Rules can reference IP ranges (CIDR) or other security groups.
- By default all inbound is denied and all outbound is allowed.

## EBS (Elastic Block Store)

EBS provides persistent block storage volumes attached to an instance, like a virtual hard disk.

- Data persists independently of the instance lifecycle.
- A volume lives in one Availability Zone and attaches to instances in that AZ.
- Types: `gp3`/`gp2` (general SSD), `io1`/`io2` (high-performance SSD), `st1`/`sc1` (HDD).
- Backups are taken as snapshots, stored in S3 and restorable to new volumes.
- Instance store is the alternative: temporary storage that is lost when the instance stops.

## Public vs Private IP

| | Private IP | Public IP |
|---|---|---|
| Reachable from | Inside the VPC | The internet |
| Persistence | Stays for the instance's life | Changes on stop/start (unless Elastic IP) |
| Use | Communication between resources | Access from outside |

An **Elastic IP** is a static public IPv4 address that you allocate to your account and attach to an instance.

## Instance Lifecycle

```text
pending → running → stopping → stopped → (start) → pending → running
                  ↘ shutting-down → terminated
```

| State | Meaning |
|---|---|
| pending | Instance is starting up |
| running | Ready to use; billed |
| stopping / stopped | Shut down; EBS volumes kept; no compute charge |
| shutting-down / terminated | Deleted permanently |

A **reboot** restarts the instance and keeps the same IPs. **Hibernate** saves memory state to the EBS root volume.

## Common Use Cases

- Hosting web and application servers.
- Running backend APIs and microservices.
- Batch processing and data analytics.
- Development and test environments.
- Hosting databases or custom software that requires full OS control.
- Auto Scaling groups behind a load balancer for scalable applications.
