## Kubectl basic commands 

> `kubectl` is the command-line interface to interact with the Kubernetes cluster via the `kube-apiserver`.

---

### Get Cluster Resources

| Command                 | Purpose                      |
| ----------------------- | ---------------------------- |
| `kubectl get pods`      | Show all pods                |
| `kubectl get svc`       | Show services                |
| `kubectl get deploy`    | Show deployments             |
| `kubectl get all`       | Show pods, services, deploys |
| `kubectl get events`    | Cluster-level event logs     |
| `kubectl get namespace` | Show namespaces              |

---

### Describe Resources 

| Command                          | Purpose                 |
| -------------------------------- | ----------------------- |
| `kubectl describe pod <pod>`     | Detailed info + events  |
| `kubectl describe deploy <name>` | View deployment rollout |
| `kubectl describe node <node>`   | Node-level detail       |

---

### Create / Apply / Delete Resources

| Command                         | Purpose                        |
| ------------------------------- | ------------------------------ |
| `kubectl apply -f <file.yaml>`  | Create/update from YAML        |
| `kubectl create -f <file.yaml>` | Create (fails if exists)       |
| `kubectl delete -f <file.yaml>` | Delete from file               |
| `kubectl delete pod <name>`     | Delete specific pod            |
| `kubectl delete all --all`      | Delete everything in namespace |


---

### Exec & Logs

| Command                               | Purpose                |
| ------------------------------------- | ---------------------- |
| `kubectl logs <pod>`                  | View logs              |
| `kubectl logs -f <pod>`               | Tail logs              |
| `kubectl exec <pod> -- <cmd>`         | Run command inside pod |
| `kubectl exec -it <pod> -- /bin/bash` | Get shell inside pod   |

---

### Port Forwarding

| Command                                              | Purpose                 |
| ---------------------------------------------------- | ----------------------- |
| `kubectl port-forward pod/<pod> <local>:<container>` | Access services locally |

---

### Rollout & Scaling

| Command                                    | Purpose                   |
| ------------------------------------------ | ------------------------- |
| `kubectl rollout status deploy/<name>`     | Check rollout status      |
| `kubectl rollout history deploy/<name>`    | View revision history     |
| `kubectl rollout undo deploy/<name>`       | Roll back to last version |
| `kubectl scale deploy <name> --replicas=N` | Change number of pods     |

---

### Namespaces

| Command                                                | Purpose               |
| ------------------------------------------------------ | --------------------- |
| `kubectl get ns`                                       | List namespaces       |
| `kubectl config set-context --current --namespace=dev` | Set default namespace |

---


