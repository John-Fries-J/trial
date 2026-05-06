# Trial Plugin

For Minecraft `1.19+` with two commands:

- `/greet <player>`: sends a configurable greeting message to an online player.
- `/timecheck`: shows the current server time using configurable timezone/message.

## Requirements

- Java 17+
- A Paper/Spigot-compatible Minecraft server (`1.19+`)

## Build

From the project root:

```bash
./gradlew build
```

After a successful build, the plugin JAR is created in `build/libs/`.

## Install

1. Copy the generated JAR from `build/libs/` into your server's `plugins/` folder.
2. Start or restart the server.
3. The plugin will generate `plugins/Trial/config.yml` on first startup.

## Usage

- `/greet <player>`
  - Sends the configured greeting to the target player.
  - If no player is provided, sender gets a usage error.
  - If player is offline/not found, sender gets an error.
- `/timecheck`
  - Displays the configured server time output in chat.

## Configuration

File: `plugins/Trial/config.yml`

```yaml
lang:
  greet:
    usage: "<gradient:#ff5f6d:#ffc371>Usage: /greet <player></gradient>"
    player-not-found: "<gradient:#ff5f6d:#ffc371>That player is not online.</gradient>"
    sent: "<gradient:#84fab0:#8fd3f4>Greeting sent to %player%.</gradient>"
    message: "<gradient:#4facfe:#00f2fe>Hello %player%!</gradient> <#ffd166>%sender%</#ffd166> <#f8f9fa>sends you a greeting.</#f8f9fa>"
  timecheck:
    usage: "<gradient:#ff5f6d:#ffc371>Usage: /timecheck</gradient>"
    message: "<gradient:#4facfe:#00f2fe>Server time:</gradient> <#f8f9fa>%time%</#f8f9fa> <#9aa0a6>(%zone%)</#9aa0a6>"
    time-format: "yyyy-MM-dd HH:mm:ss z"
    time-zone: "system"
```

Message placeholders:

- `%sender%` - command sender name
- `%player%` - target player name
- `%time%` - formatted server time
- `%zone%` - resolved timezone ID

Formatting is auto-detected:
- Legacy `&` color codes are supported (for example `&a`, `&e`, `&l`).
- MiniMessage tags are supported (for example `<#RRGGBB>` and `<gradient:#A:#B>...</gradient>`).
