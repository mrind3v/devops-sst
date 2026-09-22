# `kubectl logs` vs `kubectl events`


| Comparison | `kubectl logs` | `kubectl events` |
|---|---|---|
| Shows | Output written by a container | Actions and state changes reported by Kubernetes components |
| Helps answer | "What happened inside the application?" | "What happened to the Kubernetes resource?" |
| Common uses | Find application errors, stack traces, and request details | Find scheduling failures, image-pull errors, probe failures, and restarts |
| Target | A Pod's container | Resources in a namespace or one resource selected with `--for` |
| Previous data | Use `--previous` to read logs from the previous container instance | Events remain available only until the cluster removes them |
| Live output | Use `--follow` or `-f` | Use `--watch` or `-w` |


