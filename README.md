# ⚽ Football Scoreboard

A simple Java library for tracking **Live Football World Cup matches** in-memory.

This project demonstrates clean object-oriented design, simplicity, and testability.

---

## 🛠 Build & Test

To build and run tests:

```bash
mvn clean test
```

Or in IntelliJ:

1. Open the Maven tool window
2. Navigate to `Lifecycle` → `test`

---

## 🚀 Features

- ✅ Start a new match (with score 0–0)
- ✏️ Update a match with absolute scores
- 🏁 Finish a match (removes it)
- 📊 Get a sorted summary of matches by:
    1. **Total score (descending)**
    2. **Most recently started match (if scores tie)**

---

## 📋 Example Output

If you start the following matches in order and update scores:

1. Mexico 0 - Canada 5
2. Spain 10 - Brazil 2
3. Germany 2 - France 2
4. Uruguay 6 - Italy 6
5. Argentina 3 - Australia 1

The summary will be:

```
1. Uruguay 6 - Italy 6
2. Spain 10 - Brazil 2
3. Mexico 0 - Canada 5
4. Argentina 3 - Australia 1
5. Germany 2 - France 2
```

---

## 📦 Project Structure

- `Team.java` – represents a football team
- `Match.java` – represents a football match and score
- `Scoreboard.java` – interface defining scoreboard behavior
- `InMemoryScoreboard.java` – in-memory implementation of the scoreboard
- `InMemoryScoreboardTest.java` – JUnit tests covering all scenarios

---

## 🧠 Assumptions / Design Choices

- Match scores are always updated using **absolute values** (not increments)
- Teams are created internally when starting a match
- Summary ordering is implemented using `LinkedList` index for recency
- Edge cases like null names or negative scores are handled with exceptions

---

## ✅ Technologies

- Java 11+
- JUnit 4
- Maven

---

## 📎 License

This project is provided as a sample library implementation.