# QR Code API

This project provides a simple REST API for generating QR codes dynamically using Spring Boot and ZXing. You can customize the content, size, type, and error correction level of the QR codes.

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/IbnBaqqi/qrcode-api.git
cd qrcode-api
```

---

### 2. Run the Application

This is a **Gradle** project. To start the application:

```bash
./gradlew bootRun
```

If you're on Windows:

```bash
gradlew.bat bootRun
```

Once running, the application will be available at:

```bash
http://localhost:8080
```

---

## 📚 API Documentation

This API has no authentication or complex setup—just one endpoint to generate QR codes.

---

## 🧪 Example API Usage

### Health Check

Check if the API is up:

```bash
GET /api/health
```

---

### Generate a QR Code

```bash
GET /api/qrcode
```

#### Query Parameters

| Parameter    | Type     | Required | Default | Description |
|--------------|----------|----------|---------|-------------|
| `content`    | `string` | ✅       | –       | Text to encode in the QR code |
| `size`       | `int`    | ❌       | 250     | Image size in pixels (150–350 allowed) |
| `type`       | `string` | ❌       | png     | Image format: `png`, `jpeg`, or `gif` |
| `correction` | `char`   | ❌       | L       | Error correction level: `L`, `M`, `Q`, or `H` |

#### Example Request

```bash
curl "http://localhost:8080/api/qrcode?content=https://github.com/IbnBaqqi&size=300&type=png&correction=H" --output qrcode.png
```

---

## 🛑 Validation Rules

- `content` must **not** be blank
- `size` must be between **150 and 350**
- `type` must be one of: `png`, `jpeg`, `gif`
- `correction` must be one of: `L`, `M`, `Q`, `H`

Invalid input returns `400 Bad Request`.

---

## 🔧 Tech Stack

- Java 21
- Spring Boot
- Gradle
- ZXing

---

## 🐛 Found a Bug?

Please [open an issue](https://github.com/IbnBaqqi/qr-code-service/issues) with steps to reproduce or an example request.
