import panda as pd
import matplotlib.pyplot as plt

# Lê o arquivo CSV gerado pelo programa em C
df = pd.read_csv("resultados.csv")

# Lista de algoritmos
algoritmos = df["Algoritmo"].unique()

# Cores para os algoritmos
cores = {
    "Bubble": "red",
    "Selection": "blue",
    "Insertion": "green",
    "QuickSort": "orange"
}

# -------- TEMPO --------
plt.figure(figsize=(10, 6))
for alg in algoritmos:
    dados = df[df["Algoritmo"] == alg]
    plt.plot(dados["Tamanho"], dados["Tempo(ms)"], marker='o', label=alg, color=cores[alg])
plt.title("Tempo de Execução por Algoritmo")
plt.xlabel("Tamanho do Vetor")
plt.ylabel("Tempo (ms)")
plt.legend()
plt.grid(True)
plt.savefig("tempo_execucao.png")
plt.show()

# -------- COMPARAÇÕES --------
plt.figure(figsize=(10, 6))
for alg in algoritmos:
    dados = df[df["Algoritmo"] == alg]
    plt.plot(dados["Tamanho"], dados["Comparacoes"], marker='o', label=alg, color=cores[alg])
plt.title("Número de Comparações por Algoritmo")
plt.xlabel("Tamanho do Vetor")
plt.ylabel("Comparações")
plt.legend()
plt.grid(True)
plt.savefig("comparacoes.png")
plt.show()

# -------- MOVIMENTAÇÕES --------
plt.figure(figsize=(10, 6))
for alg in algoritmos:
    dados = df[df["Algoritmo"] == alg]
    plt.plot(dados["Tamanho"], dados["Movimentacoes"], marker='o', label=alg, color=cores[alg])
plt.title("Número de Movimentações por Algoritmo")
plt.xlabel("Tamanho do Vetor")
plt.ylabel("Movimentações")
plt.legend()
plt.grid(True)
plt.savefig("movimentacoes.png")
plt.show()
