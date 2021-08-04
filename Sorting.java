package br.com.sorting;

import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class Sorting {

	static void bucketSort(int[] vetor, int maiorValor) {

		int numBaldes = maiorValor / 5;

		LinkedList[] B = new LinkedList[numBaldes];

		for (int i = 0; i < numBaldes; i++) {
			B[i] = new LinkedList<Integer>();
		}

		// Coloca os valores no balde respectivo:
		for (int i = 0; i < vetor.length; i++) {
			int j = numBaldes - 1;
			while (true) {
				if (j < 0) {
					break;
				}
				if (vetor[i] >= (j * 5)) {
					B[j].add(vetor[i]);
					break;
				}
				j--;
			}
		}

		// Ordena e atualiza o vetor:
		int indice = 0;
		for (int i = 0; i < numBaldes; i++) {

			int[] aux = new int[B[i].size()];

			// Coloca cada balde num vetor:
			for (int j = 0; j < aux.length; j++) {
				aux[j] = (Integer) B[i].get(j);
			}

			insertionSort(aux); // algoritmo escolhido para ordenação.

			// Devolve os valores ao vetor de entrada:
			for (int j = 0; j < aux.length; j++, indice++) {
				vetor[indice] = aux[j];
			}

		}

	}

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[index]) {
					index = j;// searching for lowest index
				}
			}
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		}
	}

	static void insertionSort(int arr[]) {
		int n = arr.length;
		for (int i = 1; i < n; ++i) {
			int key = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	static void bubbleSort(int[] arr) {
		int n = arr.length;
		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < (n - i); j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];
					arr[j - 1] = arr[j];
					arr[j] = temp;
				}

			}
		}

	}

	// Metodo para printar array
	static void printArray(int[] sorted_array) {
		for (int i = 0; i < sorted_array.length; i++)
			System.out.print(sorted_array[i] + " ");
	}

	// Metodo para encontrar o maior elemento no array
	static int maxValue(int[] array) {
		int maxValue = 0;
		for (int i = 0; i < array.length; i++)
			if (array[i] > maxValue)
				maxValue = array[i];
		return maxValue;
	}

	public static void main(String args[]) {
		
				
		// Tamanho do array
		Random random = new Random();
		// N é o numero de elementos aleatorios
		int N = 1000000;
		// Cria um array de n numeros aleatorios
		int[] array = new int[N];
		for (int i = 0; i < N; i++)
			// Range de numeros aleatorios gerados, ex 0~~1000
			array[i] = Math.abs(random.nextInt(10000));
		// Determina o valor maximo no array
		int maxValue = maxValue(array);
		
		Runtime runtime = Runtime.getRuntime();
		long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Uso de memoria antes em bytes: " + usedMemoryBefore);
		long start = System.currentTimeMillis();
		
		bucketSort(array,maxValue); //O algoritmo de ordenação a ser testado
		
		long end = System.currentTimeMillis();
		System.out.println("Tempo do algoritmo: " + (end - start));
		long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("Ganho de memoria em bytes: " + (usedMemoryAfter - usedMemoryBefore));

		
		
	}
}
