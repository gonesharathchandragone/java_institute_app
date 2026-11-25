CREATE DATABASE IF NOT EXISTS sharath_institute
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE sharath_institute;

CREATE TABLE IF NOT EXISTS users (
  id            INT AUTO_INCREMENT PRIMARY KEY,
  full_name     VARCHAR(100) NOT NULL,
  email         VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS courses (
  id          INT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(150) NOT NULL,
  description VARCHAR(500),
  duration    VARCHAR(50)
);

INSERT INTO courses (title, description, duration) VALUES
('Linux Administration', 'Shell, users, permissions, networking and system services.', '3 weeks'),
('Git & GitHub', 'Version control, branching strategies and pull request workflows.', '2 weeks'),
('Docker', 'Containers, images, Docker Compose and registries.', '3 weeks'),
('Kubernetes', 'Pods, deployments, services, ingress and Helm.', '5 weeks'),
('Jenkins CI/CD', 'Build pipelines, Jenkinsfile and automated deployments.', '3 weeks'),
('Terraform', 'Infrastructure as Code on AWS.', '3 weeks'),
('Ansible', 'Configuration management with playbooks and roles.', '2 weeks'),
('AWS for DevOps', 'EC2, S3, IAM, VPC, RDS and CloudWatch.', '4 weeks');
