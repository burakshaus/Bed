# Bed 📝

**Bed** is a lightweight, distraction-free text editor built from scratch using **Java Swing**. 

It is designed to be a simple, fast, and cross-platform tool for editing text files. This project serves as a foundational implementation of a desktop GUI application, with plans to evolve into a more advanced C++ version in the future.

## 🚀 Features

- **Clean Interface:** A minimal, no-clutter workspace for focused typing.
- **Cross-Platform:** Runs on Linux, Windows, and macOS (requires Java).
- **Essential File Operations:**
  - Open existing text files (`.txt`, `.java`, `.md`, etc.)
  - Save changes to disk.
  - Create new files.
- **Native Scrolling:** Handles large files with ease.

## 🛠️ Built With

* **Language:** Java (JDK 17+)
* **GUI Framework:** Swing (javax.swing)
* **IDE:** Eclipse

## 📦 Getting Started

### Prerequisites
You need the **Java Development Kit (JDK)** installed on your system.
* **Linux (Ubuntu/Mint):** `sudo apt install openjdk-17-jdk`
* **Verify:** `java -version`

### Installation & Running
1.  **Clone the repository** (or download the source code):
    ```bash
    git clone [https://github.com/yourusername/xed.git](https://github.com/yourusername/xed.git)
    cd xed
    ```

2.  **Open in Eclipse:**
    * `File` -> `Open Projects from File System...`
    * Select the `Xed` folder.

3.  **Run the Application:**
    * Navigate to `src/Main.java`.
    * Right-click -> `Run As` -> `Java Application`.

## 🗺️ Roadmap

This project is currently in **Phase 1 (Java Prototype)**.

- [x] Basic Window & Text Area
- [ ] File Menu (Open, Save, Save As)
- [ ] Keyboard Shortcuts (Ctrl+S, Ctrl+O)
- [ ] **Dark Mode** 🌙
- [ ] Line Numbers
- [ ] Find & Replace
- [ ] **Phase 2:** Rewrite core logic in **C++ (Qt/wxWidgets)** for native performance.

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
*Created by [Your Name]*
