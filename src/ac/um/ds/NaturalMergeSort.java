package ac.um.ds;

import java.util.LinkedList;
import java.util.Queue;

public class NaturalMergeSort<T extends Comparable<T>> implements ISort<T> {
    public void sort(T[] arr, int n) {
        Queue<Integer> points = new LinkedList();
        findBorders(arr, n, points);
        int p, q, r;
        while (points.size() >= 3) {
            p = points.poll();
            if (p != n) {
                q = points.poll();
                if (q != n) {
                    r = points.poll();
                } else {
                    points.add(p);
                    points.add(q);
                    continue;
                }
            } else {
                points.add(p);
                continue;
            }
            merge(arr, p, q, r);
            points.add(p);
            points.add(r);
        }
    }

    public void findBorders(T[] arr, int n, Queue<Integer> points) {
        points.add(0);
        for (int i = 0; i < n - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                points.add(i + 1);
            }
        }
        points.add(n);
    }

    public void merge(T[] arr, int p, int q, int r) {
        int i = p, j = q, k = 0;
        T[] temp = (T[]) new Comparable[r - p];
        while (i < q && j < r) {
            if (arr[i].compareTo(arr[j]) >= 0) {
                temp[k] = arr[j];
                j++;
            } else {
                temp[k] = arr[i];
                i++;
            }
            k++;
        }
        while (i < q) {
            temp[k] = arr[i];
            i++;
            k++;
        }
        while (j < r) {
            temp[k] = arr[j];
            j++;
            k++;
        }
        for (i = p, j = 0; i < r; i++, j++) {
            arr[i] = temp[j];
        }
    }
}
