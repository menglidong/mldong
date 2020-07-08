apiVersion: v1
kind: Namespace
metadata:
  name: {{NAMESPACE}}
---
apiVersion: v1
kind: Service
metadata:
  name: {{APP_NAME}}-nodeport
  namespace: {{NAMESPACE}}
spec:
  type: NodePort
  ports:
  - port: 8080
    targetPort: 8080
  selector:
    app: {{APP_NAME}}
---
apiVersion: v1
kind: Service
metadata:
  name: {{APP_NAME}}
  namespace: {{NAMESPACE}}
spec:
  type: ClusterIP
  ports:
  - port: 8080
    protocol: TCP
    targetPort: 8080
  selector:
    app: {{APP_NAME}}
---
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{APP_NAME}}
  namespace: {{NAMESPACE}}
spec:
  selector:
    matchLabels:
      app: {{APP_NAME}}
  replicas: 1
  template:
    metadata:
      labels:
        app: {{APP_NAME}}
    spec:
      containers:
        - name: {{APP_NAME}}
          image: {{IMAGE_URL}}:{{IMAGE_TAG}}
          imagePullPolicy: IfNotPresent
          ports:
            - containerPort: 8080
              name: port
              protocol: TCP
          command: ["/bin/sh"]
          args: ["-c", "set -e && java -jar app.jar --spring.profiles.active={{PROFILES}} --server.port=8080"]
---
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  annotations:
  name: {{APP_NAME}}-ingress
  namespace: {{NAMESPACE}}
spec:
  rules:
    - host: {{HOST}}
      http:
        paths:
          - backend:
              serviceName: {{APP_NAME}}
              servicePort: 8080
            path: /
