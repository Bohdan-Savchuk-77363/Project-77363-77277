# README.md

## 1. Informacje o Zespole
*   **Nazwa zespołu:** Hot Fisher
*   **Skład zespołu:**
    *   Bohdan Savchuk (Numer albumu: 77363)
    *   Oleksandr Paduh (Numer albumu: 77277)

## 2. Opis Projektu
Projekt **Vistula Book** (znany również jako "Project-77363-77277") to nowoczesna aplikacja webowa zbudowana w oparciu o framework **Java Spring Boot**. System integruje się z bazą danych **PostgreSQL** i jest w pełni skonteneryzowany przy użyciu **Docker**. Aplikacja została zaprojektowana z myślą o wydajności algorytmicznej oraz skalowalności w chmurze.

## 3. Sprawdzone Platformy i Publiczne URL-e
Aplikacja została pomyślnie wdrożona i przetestowana na wielu platformach chmurowych:

*   **AWS Elastic Beanstalk (Główna platforma):** [Link do AWS](https://libraryvistula-hjfja6hbeth5amg0.polandcentral-01.azurewebsites.net/)
    *   Wykorzystany jako główne środowisko produkcyjne dla warstwy backendowej.
*   **Render (Full Stack: App + DB):** [https://project-77363-77277.onrender.com/](https://project-77363-77277.onrender.com/)
    *   Platforma hostuje zarówno aplikację Java Spring, jak i dedykowaną instancję bazy danych PostgreSQL.
*   **Railway:** [https://project-77363-77277-production.up.railway.app/](https://project-77363-77277-production.up.railway.app/)
    *   Użyty do szybkiego wdrożenia kontenerowego z pełną integracją CI/CD.
*   **Fly.io:** [Link do Fly.io]
    *   Platforma służąca do globalnego hostingu kontenerów Docker.
*   **Microsoft Azure:** [https://libraryvistula-hjfja6hbeth5amg0.polandcentral-01.azurewebsites.net/](https://libraryvistula-hjfja6hbeth5amg0.polandcentral-01.azurewebsites.net/)
    *   Środowisko wykorzystane podczas wstępnych faz testów.

**Endpointy do sprawdzenia:** `/api/health` lub `/home`

## 4. Instrukcja Uruchomienia Lokalnego (Budowa od zera)
Aby uruchomić projekt po raz pierwszy на новим комп'ютері:

1.  **Sklonowanie repozytorium:**
    ```bash
    git clone https://github.com/Bohdan-Savchuk-77363/Project-77363-77277.git
    cd Project-77363-77277
    ```
2.  **Budowanie aplikacji (mvnw):**
    ```bash
    ./mvnw clean package -DskipTests
    ```
3.  **Tworzenie i inicjalizacja kontenerów:**
    ```bash
    docker-compose up --build
    ```
4.  **Dostęp:** Aplikacja będzie dostępna pod adresem [http://localhost:5000](http://localhost:5000).

## 5. Analiza Hostingów i Wyzwania Techniczne
### Co się udało?
*   Skuteczna konteneryzacja i separacja warstwy bazy danych (Render) od logiki (AWS).
*   Poprawna konfiguracja zmiennych środowiskowych w AWS Elastic Beanstalk.

### Co się nie udało / Problemy:
*   **Uprawnienia IAM:** Trudności z błędem `AWSEBInstanceLaunchWaitCondition` na AWS.
*   **Konfiguracja Portów:** Konieczność wymuszenia portu `5000` dla AWS.

### Porównanie platform:
*   **Najłatwiejsza:** **Render** — prosty interfejs i automatyczne JDBC.
*   **Najtrudniejsza:** **AWS Elastic Beanstalk** — wymaga zaawansowanej wiedzy o VPC i rolach IAM.

## 6. Szczegóły Techniczne
*   **Wersja Javy:** Corretto 17 (Java 17).
*   **Budowanie:** Maven, plik `.jar` (ok. 50 MB).
*   **Zmienne:** `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`.

## 7. Podsumowanie i Wnioski Końcowe
Zespołowi **Hot Fisher** udało się zrealizować wszystkie założenia projektu. Aplikacja działa stabilnie na wielu platformach chmurowych.

**Uwagi dotyczące platformy Microsoft Azure:**
Głównym wyzwaniem były restrykcje Azure, które mimo posiadania konta studenckiego uniemożliwiły pełne wykorzystanie platformy:
*   **Wymagania płatnicze:** Konieczność weryfikacji karty płatniczej mimo statusu studenta.
*   **Brak wsparcia dla konta studenckiego:** System nie zaakceptował uprawnień studenckich do darmowego hostingu kontenerów Docker, co zmusiło zespół do pełnego przejścia na AWS та Render.