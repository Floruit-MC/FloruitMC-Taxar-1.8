
# 💰 FloruitTaxar - Sistema de Taxação de Facções

O **FloruitTaxar** é um plugin para servidores que utilizam o plugin **MassiveCraft Factions**, permitindo que staff aplique uma **taxa percentual** sobre o banco de uma facção, com **motivo personalizado** e feedback via mensagens clicáveis. Ideal para punições administrativas, ações moderadas ou eventos especiais.

---

## ⚙️ Funcionalidades

- 📌 Aplicação de taxa em % no banco da facção.
- 💬 Motivo personalizado para a taxação.
- 📎 Mensagens clicáveis para facilitar a escolha do motivo.
- 🧾 Mensagens configuráveis via `MessageConfig`.

---

## 🧪 Comando

### `/taxar <jogador> [motivo] [porcentagem]`

Comando principal utilizado para aplicar a taxa.

| Parâmetro        | Descrição                                                                 |
|------------------|---------------------------------------------------------------------------|
| `<jogador>`      | Jogador alvo pertencente a uma facção.                                    |
| `[motivo]`       | Motivo da taxação. Se omitido, opções clicáveis são exibidas.             |
| `[porcentagem]`  | Valor da taxa aplicada (entre 1 e 100). Padrão: `50%`.                    |

---

## 🧾 Exemplos de Uso

- `/taxar Notch`  
  Exibe lista de motivos clicáveis com taxa padrão de 50%.

- `/taxar Notch Abuso de bug 25`  
  Taxa a facção de `Notch` em **25%** com motivo `"Abuso de bug"`.

---

## 🔒 Permissões

| Permissão                     | Ação                                 |
|------------------------------|--------------------------------------|
| `floruitaxar.factions`       | Permissão necessária para usar `/taxar`. |

---

## 🧠 Comportamento do Comando

- Caso o jogador alvo **não esteja online** ou **não tenha facção**, mensagens específicas são exibidas.
- Se o motivo não for definido, são apresentadas **opções sugeridas clicáveis**:
  - **Banimento**
  - **Trapaça**
  - **Desrespeito**
- A porcentagem deve estar entre **1% e 100%**.

---

## 🛠️ Mensagens Configuráveis

As mensagens exibidas pelo comando são obtidas via `MessageConfig`. Exemplos de chaves:

```yaml
usage: "§cUso correto: /taxar <jogador> [motivo] [porcentagem]"
player-not-found: "§cJogador não encontrado ou offline."
no-faction: "§cEste jogador não possui facção."
choose-reason: "§eEscolha o motivo da taxação:"
no-reason: "§cMotivo não pode estar vazio."
invalid-percentage: "§cPorcentagem inválida."
invalid-percentage-range: "§cA porcentagem deve ser entre 1% e 100%."
tax-success: "§aFacção taxada com sucesso."
```

---

## 🧩 Dependências

- [Aikar Commands](https://github.com/aikar/commands)
- [MassiveCraft Factions](https://www.spigotmc.org/resources/factions.1900/)

---

## ✅ Sugestões Futuras

- 📈 Registro de logs das taxações.
- 📦 Integração com banco de dados.
- 📊 Interface gráfica para escolha do motivo e porcentagem.
