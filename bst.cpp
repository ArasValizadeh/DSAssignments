#include <iostream>
#include <string>
#include <sstream>

class Node {
public:
    Node* left;
    Node* right;
    int data;

    Node(int data) : data(data), left(nullptr), right(nullptr) {}
};

class BST {
public:
    Node* root;

    BST() : root(nullptr) {}

    void insert(int data) {
        root = insertNode(root, data);
    }

    Node* insertNode(Node* root, int data) {
        if (!root) {
            return new Node(data);
        }
        if (data < root->data) {
            root->left = insertNode(root->left, data);
        } else {
            root->right = insertNode(root->right, data);
        }
        return root;
    }

    void erase(int data) {
        int tedad = CountOfKey(root, data);
        for (int i = 0; i < tedad; i++) {
            root = deleteNode(root, data);
        }
    }

    Node* deleteNode(Node* root, int data) {
        if (!root) {
            return nullptr;
        }
        if (data < root->data) {
            root->left = deleteNode(root->left, data);
        } else if (data > root->data) {
            root->right = deleteNode(root->right, data);
        } else {
            if (!root->left && !root->right) {
                return nullptr;
            } else if (!root->left) {
                return root->right;
            } else if (!root->right) {
                return root->left;
            } else {
                Node* copy = root->right;
                int value = copy->data;
                while (copy->left) {
                    copy = copy->left;
                    value = copy->data;
                }
                root->data = value;
                root->right = deleteNode(root->right, root->data);
            }
        }
        return root;
    }

    void print_inorder(Node* root) {
        if (root) {
            print_inorder(root->left);
            std::cout << root->data << " ";
            print_inorder(root->right);
        }
    }

    void print_preorder(Node* root) {
        if (root) {
            std::cout << root->data << " ";
            print_preorder(root->left);
            print_preorder(root->right);
        }
    }

    void print_postorder(Node* root) {
        if (root) {
            print_postorder(root->left);
            print_postorder(root->right);
            std::cout << root->data << " ";
        }
    }

    int CountOfKey(Node* root, int value) {
        int sum = 0;
        if (!root) {
            return 0;
        }
        if (root->data < value) {
            sum += CountOfKey(root->right, value);
        } else if (root->data > value) {
            sum += CountOfKey(root->left, value);
        } else {
            sum += CountOfKey(root->right, value);
            sum++;
        }
        return sum;
    }
};

int main() {
    BST tree;
    std::string line;
    while (std::getline(std::cin, line)) {
        std::istringstream iss(line);
        std::string cmd;
        iss >> cmd;
        if (cmd == "insert") {
            int val;
            iss >> val;
            tree.insert(val);
        } else if (cmd == "erase") {
            int delVal;
            iss >> delVal;
            tree.erase(delVal);
        } else if (cmd == "print_inorder") {
            if (!tree.root) {
                std::cout << "empty" << std::endl;
            } else {
                tree.print_inorder(tree.root);
                std::cout << std::endl;
            }
        } else if (cmd == "print_preorder") {
            if (!tree.root) {
                std::cout << "empty" << std::endl;
            } else {
                tree.print_preorder(tree.root);
                std::cout << std::endl;
            }
        } else if (cmd == "print_postorder") {
            if (!tree.root) {
                std::cout << "empty" << std::endl;
            } else {
                tree.print_postorder(tree.root);
                std::cout << std::endl;
            }
        }
    }
    return 0;
}
