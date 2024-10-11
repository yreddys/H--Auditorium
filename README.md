

---

### **Jenkins Pipeline Configuration**

```markdown
## Jenkins Pipeline Configuration

```groovy
pipeline {    
    agent any 
    tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    stages {
        stage('Git Checkout') {
            steps {
                git branch: 'main', credentialsId: 'git-cred', url: 'https://github.com/jaiswaladi246/Boardgame.git'
            }
        }
        stage('Compile') {
            steps {
                sh "mvn compile"
            }
        }
        stage('Build') {
            steps {
                sh "mvn package"
            }
        }
        stage('Build & Tag Docker Image') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'docker-cred', toolName: 'docker') {
                        sh "docker build -t yreddys211/boardshack:latest ."
                    }
                }
            }
        }
        stage('Deploy To Kubernetes') {
            steps {
                withKubeConfig(caCertificate: '', clusterName: 'kubernetes', contextName: '', credentialsId: 'k8s-cred', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://172.31.35.85:6443') {
                    sh "kubectl apply -f deployment-service.yaml"
                }
            }
        }
        stage('Verify the Deployment') {
            steps {
                withKubeConfig(caCertificate: '', clusterName: 'kubernetes', contextName: '', credentialsId: 'k8s-cred', namespace: 'webapps', restrictKubeConfigAccess: false, serverUrl: 'https://172.31.35.85:6443') {
                    sh "kubectl get pods -n webapps"
                    sh "kubectl get svc -n webapps"
                }
            }
        }
    }
}
```
```

---



### 1. Update System Packages (On Master & Worker Node)
```bash
sudo apt-get update
```

### 2. Install Docker (On Master & Worker Node)
```bash
sudo apt install docker.io -y
sudo chmod 666 /var/run/docker.sock
```

### 3. Install Required Dependencies for Kubernetes (On Master & Worker Node)
```bash
sudo apt-get install -y apt-transport-https ca-certificates curl gnupg
sudo mkdir -p -m 755 /etc/apt/keyrings
```

### 4. Add Kubernetes Repository and GPG Key (On Master & Worker Node)
```bash
curl -fsSL https://pkgs.k8s.io/core:/stable:/v1.28/deb/Release.key | sudo gpg --dearmor -o /etc/apt/keyrings/kubernetes-apt-keyring.gpg
echo 'deb [signed-by=/etc/apt/keyrings/kubernetes-apt-keyring.gpg] https://pkgs.k8s.io/core:/stable:/v1.28/deb/ /' | sudo tee /etc/apt/sources.list.d/kubernetes.list
```

### 5. Update Package List (On Master & Worker Node)
```bash
sudo apt update
```

### 6. Install Kubernetes Components (On Master & Worker Node)
```bash
sudo apt install -y kubeadm=1.28.1-1.1 kubelet=1.28.1-1.1 kubectl=1.28.1-1.1
```

### 7. Initialize Kubernetes Master Node (On Master Node)
```bash
sudo kubeadm init --pod-network-cidr=10.244.0.0/16
```

### 8. Configure Kubernetes Cluster (On Master Node)
```bash
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config
```

### 9. Deploy Networking Solution (Calico) (On Master Node)
```bash
kubectl apply -f https://docs.projectcalico.org/v3.20/manifests/calico.yaml
```

### 10. Deploy Ingress Controller (NGINX) (On Master Node)
```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.49.0/deploy/static/provider/baremetal/deploy.yaml
```

```

