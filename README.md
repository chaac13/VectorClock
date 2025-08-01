﻿# VectorClock
# Trabalho Prático – Sistemas Distribuídos (2025/1)

Este projeto foi desenvolvido por Bruno Peres e Caio Cordeiro e simula um sistema distribuído com múltiplos processos (ou threads) que utilizam **Relógios Vetoriais (Vector Clocks)** para controlar a **ordem causal dos eventos**.

## 🎯 Objetivo

Simular a execução de eventos locais, envio e recebimento de mensagens entre processos, mantendo o controle da causalidade utilizando relógios vetoriais. O sistema permite verificar se um evento ocorreu **antes**, **depois** ou é **concorrente** com outro evento — algo que os relógios lógicos de Lamport não conseguem representar completamente.

---

## ✅ Requisitos Atendidos

### 1. Inicialização de Processos
- O sistema permite configurar **N processos** (definidos no início da execução).
- Cada processo mantém um **vetor de relógio de tamanho N**, atualizado durante a simulação.

### 2. Execução de Eventos
Cada processo pode:
- Executar **eventos locais**.
- **Enviar mensagens** para outros processos (com seu vetor de tempo).
- **Receber mensagens**, atualizando seu vetor conforme as regras do relógio vetorial.

### 3. Atualização do Relógio Vetorial
- **Evento local**: incrementa seu próprio índice no vetor.
- **Envio de mensagem**: incrementa o índice do processo antes de enviar.
- **Recebimento de mensagem**: para cada posição `i`, calcula `clock[i] = max(local[i], recebido[i])`, e depois incrementa o próprio índice.

### 4. Registro de Eventos
- Todos os eventos (locais, envio, recebimento) são registrados e impressos com o **vetor de tempo no momento do evento**.

---

## ▶️ Funcionamento

Ao executar o programa, cada processo (simulado por uma `Thread`) executa eventos com base em um script ou aleatoriamente. A comunicação é feita por estruturas de dados sincronizadas (por exemplo, `BlockingQueue`), garantindo consistência na simulação.

## ▶️ Exemplo de Execução

Ao rodar o programa, cada processo simula ações como:
- [Processo 0] Evento local. Relógio: [1, 0, 0]
- [Processo 0] Enviou mensagem para Processo 2. Relógio: [2, 0, 0]
- [Processo 2] Recebeu mensagem de Processo 0. Relógio: [2, 0, 1]
- [Processo 2] Evento local. Relógio: [2, 0, 2]

Esses logs indicam como o vetor de cada processo é atualizado ao longo da execução, preservando a relação causal.
