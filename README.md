## Для запуска приложения:
1. Запустить Docker;
2. Открыть проект в IntelliJ IDEA;
3. В терминале IntelliJ IDEA запустить необходимые контейнеры командой docker-compose up;
4. В новой вкладке терминала запустить приложение java командой: java -jar .\artifacts\aqa-shop.jar
5. Проверить доступность приложения в браузере по адресу:
   http://localhost:8080/

## Для запуска авто-тестов:
1. В новой вкладке терминала прописать команду: ./gradlew clean test
2. По завершению тестов выполнить команду ./gradlew allureServe

## Для просмотра отчетов:
- Если отчет не открывается автоматически в браузере, то выполнить команду: ./gradlew allureReport и открыть отчет вручную (файл index.html) по адресу: .\build\reports\allure-report\allureReport