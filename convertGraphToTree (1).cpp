#include <iostream>
#include <vector>

using namespace std;


int number1 = 0 ;
int number2 = 0 ;
int removed = 0 ;

vector<int> adjustment[3500];
vector<int> component[3500];
int visited[3500];
int visitInSearch[3500];
int matrix[3500][3500];

void exploreConnectedComponent(int startVertex, int componentIndex) ;

void DFS(int currentVertex, int parentVertex)  ;

void printRemovedEdges(int number) ; 

void printConnectedComponents(int number); 

void exploreConnectedComponent(int startVertex, int componentIndex);

void printConnectedComponents(int number)  ; 

void DFS(int currentVertex, int parentVertex) {
    visited[currentVertex] = 1;
    for (int neighbor : adjustment[currentVertex]) {
        if (!visited[neighbor]) {
            DFS(neighbor, currentVertex);
        } else {
            if (neighbor != parentVertex) {
                removed++;
                matrix[neighbor][currentVertex] = matrix[currentVertex][neighbor] = -1;
            }
        }
    }
}

void printRemovedEdges(int number) {
    int output = removed / 2 ; 
    cout << output << '\n';
    for (int i = 0; i <= number; i++) {
        for (int j = 0 ; j <= number; j++) {
            if (j > i && matrix[i][j] == -1) {
                cout << i << ' ' << j << '\n';
            }
        }
    }
}

void printConnectedComponents(int number) {
    int sum = 0;
    for (int i = 1; i <= number; i++) {
        if (!visitInSearch[i]) {
            exploreConnectedComponent(i, sum);
            sum ++ ;
        }
    }
    cout << sum - 1 << '\n';
    for (int i = 1; i < sum; i++) {
        cout << component[0][0] << ' ' << component[i][0] << '\n';
    }
}

void exploreConnectedComponent(int startVertex, int componentIndex) {
    visitInSearch[startVertex] = 1;
    component[componentIndex].push_back(startVertex);
    for (int i = 0; i < adjustment[startVertex].size(); i++) {
        int neighbor = adjustment[startVertex][i];
        if (!visitInSearch[neighbor]) {
            exploreConnectedComponent(neighbor, componentIndex);
        }
    }
}


int main() {
    cin >> number1 >> number2;

    for (int i = 1; i <= number1; i++) {
        adjustment[i] = vector<int>();
        component[i] = vector<int>();
    }

    for (int i = 0; i < number2; i++) {
        int x, y;
        cin >> x >> y;
        matrix[x][y] = matrix[y][x] = 1;
        adjustment[y].push_back(x);
        adjustment[x].push_back(y);
    }

    for (int i = 1; i <= number1; i++) {
        if (visited[i] == 0 ) {
            DFS(i, -1);
        }
    }

    printRemovedEdges(number1);

    printConnectedComponents(number1) ;
}
