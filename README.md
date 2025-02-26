# Jenkins Shared Libraries

This repository contains reusable pipeline steps for Jenkins, designed to streamline and standardize our CI/CD workflows.

## Overview

This shared library provides a collection of Groovy scripts (in `vars/`) that encapsulate common Jenkins pipeline tasks. These scripts are designed to be easily integrated into your Jenkins pipelines, promoting code reuse and consistency.

## Integreating Jenkins Shared Library

**Configure Jenkins**

- In your Jenkins instance, navigate to "Manage Jenkins" -> "Configure System".
- Scroll down to the "Global Pipeline Libraries" section.
- Click "Add".
- Enter a name for your library (e.g., `shared-libraries`).
- Set the "Default version" to `main` (or your desired branch).
- Set the "Retrieval method" to "Modern SCM".
- Select "Git" as the SCM.
- Enter the repository URL for this shared library.
- Add credentials if private repository.
- Save your changes.

**Using in Pipeline**

```groovy
@Library("shared-libraries") _

pipeline {
    agent any

    stages {
        stage("Code Cloning") {
            steps {
                code_clone("https://www.github.com/Abdullah-0-3/NetflixCloneK8s.git", "main")
            }
        }
    }
}
```

> * Replace placeholder values (e.g., `your-repo-url`, `your-image-name`) with your actual values.
> * Refer to the individual `.groovy` files in the `vars/` directory for specific parameter details.

## Contributing

Feel free to contribute to this library by submitting pull requests.

---
