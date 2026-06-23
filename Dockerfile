# Langkah 1: Gunakan Tomcat dengan Java 17 dari Temurin yang sudah kebal bug cgroup v2
FROM tomcat:9.0-jdk17-temurin

# Langkah 2: Bersihkan server bawaan
RUN rm -rf /usr/local/tomcat/webapps/*

# Langkah 3: Masukkan aplikasi LogisTel ke server
# COPY LogisTel.war /usr/local/tomcat/webapps/ROOT.war

# Langkah 3: Masukkan aplikasi LogisTel dan paksa menjadi aplikasi utama (ROOT)
COPY target/PBO-Fix-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Langkah 4: Buka gerbang port internal
EXPOSE 8080

# Langkah 5: Jalankan adaptasi port, lalu nyalakan Tomcat
CMD sed -i "s/port=\"8080\"/port=\"$PORT\"/g" /usr/local/tomcat/conf/server.xml && catalina.sh run