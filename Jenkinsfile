pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21'
            args '-e HOME=/tmp'
        }
    }
    stages {
//        stage('Checkout SCM') {
//            steps {
//                git branch: 'feature/matricula-testing', url: 'https://github.com/ronalsistemas/casani-matricula.git'
//            }
//        }
        stage('Compile') {
            steps {
                echo 'Hola Mundo'
                sh 'mvn clean compile -B -ntp'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Dtest=EstudianteServiceTest -B -ntp'
                junit 'target/surefire-reports/*.xml'

                discoverReferenceBuild()
                recordCoverage(tools: [[parser: 'JACOCO']])
            }
        }
    }
}