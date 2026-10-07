# VPC: Virtual Private Cloud (Networking)

## What is VPC?

Amazon VPC lets you create a logically isolated virtual network in AWS where you launch resources such as EC2 and RDS. You control the IP address range, subnets, routing and network security. A VPC is regional and spans all Availability Zones in that region. Every account has a **default VPC** in each region.

## CIDR

CIDR (Classless Inter-Domain Routing) notation defines an IP range as `address/prefix`, for example `10.0.0.0/16`.

| CIDR | Number of IPs |
|---|---|
| `/16` | 65,536 |
| `/24` | 256 |
| `/28` | 16 |

A VPC CIDR must be between `/16` and `/28`. Use private ranges (`10.0.0.0/8`, `172.16.0.0/12`, `192.168.0.0/16`). AWS reserves 5 IPs in every subnet.

## Subnets

A subnet is a range of IPs inside the VPC CIDR, located in **one Availability Zone**. Subnets divide the VPC into smaller networks, for example `10.0.1.0/24` (public) and `10.0.2.0/24` (private). Spreading subnets across AZs gives high availability.

## Route Tables

A route table contains rules (routes) that decide where network traffic from a subnet is directed. Each subnet is associated with one route table.

| Destination | Target |
|---|---|
| `10.0.0.0/16` | `local` |
| `0.0.0.0/0` | `igw-xxxx` (public subnet) or `nat-xxxx` (private subnet) |

The `local` route allows communication within the VPC.

## Internet Gateway (IGW)

An Internet Gateway is a horizontally scaled, highly available component attached to a VPC that allows two-way communication between the VPC and the internet. A subnet needs a route `0.0.0.0/0 → IGW` and instances need a public IP for internet access.

## NAT Gateway

A NAT Gateway lets instances in a **private subnet** initiate outbound connections to the internet (for updates, APIs) while preventing the internet from initiating connections to them.

- Placed in a **public subnet** and assigned an Elastic IP.
- The private subnet's route table has `0.0.0.0/0 → NAT Gateway`.
- Managed by AWS and scales automatically; deployed per AZ for resilience.

## Security Groups

- Operate at the **instance (network interface) level**.
- **Stateful**: return traffic is automatically allowed.
- **Allow rules only**; all else is denied.
- Evaluate all rules before deciding.

## Network ACLs (NACLs)

- Operate at the **subnet level**.
- **Stateless**: inbound and outbound rules must both allow the traffic.
- Support both **allow and deny** rules.
- Rules are evaluated in numbered order; the first match applies.
- The default NACL allows all traffic.

| | Security Group | NACL |
|---|---|---|
| Level | Instance | Subnet |
| State | Stateful | Stateless |
| Rules | Allow only | Allow and deny |
| Evaluation | All rules | In order |

## Public vs Private Subnet

| | Public subnet | Private subnet |
|---|---|---|
| Route to internet | `0.0.0.0/0 → Internet Gateway` | `0.0.0.0/0 → NAT Gateway` (or none) |
| Instance public IP | Yes | No |
| Reachable from internet | Yes (if allowed by SG/NACL) | No |
| Typical resources | Load balancers, bastion hosts, web servers | Application servers, databases |

```text
Internet
   |
[Internet Gateway]
   |
 VPC 10.0.0.0/16
 ├── Public subnet  10.0.1.0/24  (web server, NAT Gateway)
 └── Private subnet 10.0.2.0/24  (app server, database)
```
