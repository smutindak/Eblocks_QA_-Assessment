FROM selenium/standalone-firefox:122.0

# Define Environmental Variables
ENV MAVEN_VERSION=3.9.2
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "/root/.m2"
ENV PATH $MAVEN_HOME/bin:$PATH

USER root

# Set work directory for the project
WORKDIR /app

# Install necessary dependencies
RUN apt-get update && \
    apt-get install -y  openjdk-17-jdk unzip wget  && \
    mkdir -p /usr/share/maven /usr/share/maven/ref && \
    wget -q -O /tmp/apache-maven.tar.gz https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz && \
    tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 && \
    rm -f /tmp/apache-maven.tar.gz && \
    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the pom.xml file first so as to download the project dependencies
COPY pom.xml .

# Download the project dependencies
RUN mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies && \
    mvn de.qaware.maven:go-offline-maven-plugin:resolve-dependencies -DdownloadSources -DdownloadJavadoc

# Copy the rest of the project files
COPY . .
