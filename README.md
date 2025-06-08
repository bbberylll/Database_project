# Database_project


Train Ticket Reservation System

이 문서는 프로젝트 소스를 컴파일하고 실행하는 방법을 설명합니다.

1. 사전 준비

Java JDK 11 이상 설치

MySQL 서버 구동 및 데이터베이스 설정 완료

lib/mysql-connector-j-9.3.0.jar 파일이 lib 폴더에 위치

2. 프로젝트 구조

project-root/
├─ src/            # .java 소스 파일들
├─ bin/            # 컴파일된 .class 파일 출력 폴더
├─ lib/            # 외부 라이브러리 (MySQL Connector/J)
└─ README.txt      # 본 설명서

3. 컴파일

Windows CMD를 사용하는 경우, 프로젝트 루트 디렉토리에서 아래 명령어를 실행합니다:

for /R src %%F in (*.java) do javac -d bin -classpath "lib\mysql-connector-j-9.3.0.jar" "%%F"

4. 실행

컴파일이 완료되면, main.Main 클래스를 다음과 같이 실행합니다:

java -classpath bin;lib\mysql-connector-j-9.3.0.jar main.Main

main.Main: 실제 애플리케이션이 시작되는 메인 클래스

클래스패스에 bin과 MySQL 커넥터 JAR을 포함해야 합니다
