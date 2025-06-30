
package com.mycompany.sistemaclinica;


public class ArbolAVL {
    private NodoAVL root;

    public ArbolAVL() {
        this.root = null;
    }

    // --- Métodos Auxiliares para AVL ---

    // Obtiene la altura de un nodo. Si el nodo es nulo, su altura es 0.
    private int height(NodoAVL node) {
        return (node == null) ? 0 : node.height;
    }

    // Calcula el factor de balance de un nodo (altura_izquierda - altura_derecha).
    // Un factor de balance > 1 o < -1 indica desequilibrio.
    private int getBalance(NodoAVL node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Actualiza la altura de un nodo basándose en las alturas de sus hijos.
    private void updateHeight(NodoAVL node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    // --- Operaciones de Rotación (Clave para AVL) ---

    // Rotación simple a la derecha. Usada cuando el subárbol izquierdo del hijo izquierdo es muy alto.
    private NodoAVL rightRotate(NodoAVL y) {
        NodoAVL x = y.left; // x es el hijo izquierdo de y
        NodoAVL T2 = x.right; // T2 es el subárbol derecho de x

        // Realiza la rotación
        x.right = y;
        y.left = T2;

        // Actualiza las alturas de y y x, en ese orden (y primero porque es el "antiguo" padre de x)
        updateHeight(y);
        updateHeight(x);

        return x; // x es ahora la nueva raíz del subárbol rotado
    }

    // Rotación simple a la izquierda. Usada cuando el subárbol derecho del hijo derecho es muy alto.
    private NodoAVL leftRotate(NodoAVL x) {
        NodoAVL y = x.right; // y es el hijo derecho de x
        NodoAVL T2 = y.left; // T2 es el subárbol izquierdo de y

        // Realiza la rotación
        y.left = x;
        x.right = T2;

        // Actualiza las alturas de x y y, en ese orden (x primero)
        updateHeight(x);
        updateHeight(y);

        return y; // y es ahora la nueva raíz del subárbol rotado
    }

    // --- Operación de Inserción ---

    public void insert(PacientesEncoladosClinica pacientesClinica) {
        root = insertRecursive(root, pacientesClinica);
        System.out.println("Paciente con ID " + pacientesClinica.getId() + " registrado/actualizado en cola.");
    }

    // Método recursivo para insertar y balancear el árbol.
    private NodoAVL insertRecursive(NodoAVL node, PacientesEncoladosClinica pacientesClinica) {
        // 1. Realizar la inserción estándar de un BST.
        if (node == null) {
            return new NodoAVL(pacientesClinica);
        }

        if (pacientesClinica.getId() < node.data.getId()) {
            node.left = insertRecursive(node.left, pacientesClinica);
        } else if (pacientesClinica.getId() > node.data.getId()) {
            node.right = insertRecursive(node.right, pacientesClinica);
        } else {
            // Si el ID ya existe, actualizamos los datos del paciente y no hacemos más.
            node.data = pacientesClinica;
            return node;
        }

        // 2. Actualizar la altura del nodo actual después de la inserción.
        updateHeight(node);

        // 3. Obtener el factor de balance para verificar si hay desequilibrio.
        int balance = getBalance(node);

        // 4. Realizar rotaciones si el árbol está desequilibrado.
        // Hay 4 casos posibles de desequilibrio, cada uno requiere una o dos rotaciones.

        // Caso Izquierda-Izquierda (LL): El nuevo nodo se insertó en el subárbol izquierdo del hijo izquierdo.
        if (balance > 1 && pacientesClinica.getId() < node.left.data.getId()) {
            return rightRotate(node);
        }

        // Caso Derecha-Derecha (RR): El nuevo nodo se insertó en el subárbol derecho del hijo derecho.
        if (balance < -1 && pacientesClinica.getId() > node.right.data.getId()) {
            return leftRotate(node);
        }

        // Caso Izquierda-Derecha (LR): El nuevo nodo se insertó en el subárbol derecho del hijo izquierdo.
        // Se necesita una rotación a la izquierda en el hijo izquierdo, y luego una rotación a la derecha en el nodo actual.
        if (balance > 1 && pacientesClinica.getId() > node.left.data.getId()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Derecha-Izquierda (RL): El nuevo nodo se insertó en el subárbol izquierdo del hijo derecho.
        // Se necesita una rotación a la derecha en el hijo derecho, y luego una rotación a la izquierda en el nodo actual.
        if (balance < -1 && pacientesClinica.getId() < node.right.data.getId()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Si no hay desequilibrio, simplemente devolvemos el nodo (sin cambios).
        return node;
    }

    // --- Operación de Búsqueda ---

    public PacientesEncoladosClinica search(int id) {
        // Llama al método recursivo para hacer la búsqueda.
        PacientesEncoladosClinica pacientesEncontrados = encontrarPacientes(root, id);
        if (pacientesEncontrados != null) {
            System.out.println("Paciente en cola encontrado: " + pacientesEncontrados);
        } else {
            System.out.println("Paciente con ID " + id + " no encontrado en la cola.");
        }
        return pacientesEncontrados;
    }

    // Método recursivo para buscar un paciente.
    private PacientesEncoladosClinica encontrarPacientes(NodoAVL current, int id) {
        if (current == null) {
            return null; // El paciente no está en el árbol.
        }
        if (id == current.data.getId()) {
            return current.data; // Paciente encontrado.
        }
        if (id < current.data.getId()) {
            return encontrarPacientes(current.left, id); // Buscar en el subárbol izquierdo.
        } else {
            return encontrarPacientes(current.right, id); // Buscar en el subárbol derecho.
        }
    }

    // --- Operación de Eliminación ---

    /**
     * Elimina un paciente de la cola activa por su ID.
     * @param id El ID del paciente a eliminar.
     */
    public void delete(int id) {
        root = eliminarPacientes(root, id);
        System.out.println("Paciente con ID " + id + " eliminado de la cola.");
    }

    // Método recursivo para eliminar un nodo y balancear el árbol.
    private NodoAVL eliminarPacientes(NodoAVL node, int id) {
        // 1. Realizar la eliminación estándar de un BST.
        if (node == null) {
            System.out.println("Paciente con ID " + id + " no encontrado en la cola para eliminar.");
            return null;
        }

        if (id < node.data.getId()) {
            node.left = eliminarPacientes(node.left, id);
        } else if (id > node.data.getId()) {
            node.right = eliminarPacientes(node.right, id);
        } else {
            // Nodo a eliminar encontrado.

            // Caso 1: El nodo no tiene hijos o tiene un solo hijo.
            if (node.left == null || node.right == null) {
                NodoAVL temp = null;
                if (node.left == null) {
                    temp = node.right;
                } else {
                    temp = node.left;
                }

                // No tiene hijos.
                if (temp == null) {
                    return null; // El nodo se elimina simplemente.
                } else { // Tiene un hijo.
                    node = temp; // El nodo es reemplazado por su único hijo.
                }
            } else {
                // Caso 2: El nodo tiene dos hijos.
                // Encontramos el sucesor in-order (el más pequeño en el subárbol derecho).
                NodoAVL temp = minValueNode(node.right);
                // Copiamos los datos del sucesor al nodo actual.
                node.data = temp.data;
                // Eliminamos el sucesor in-order del subárbol derecho.
                node.right = eliminarPacientes(node.right, temp.data.getId());
            }
        }

        // Si el árbol se volvió vacío después de eliminar el último nodo.
        if (node == null) {
            return node;
        }

        // 2. Actualizar la altura del nodo actual.
        updateHeight(node);

        // 3. Obtener el factor de balance de este nodo.
        int balance = getBalance(node);

        // 4. Realizar rotaciones si el árbol está desequilibrado después de la eliminación.
        // Los mismos 4 casos que en la inserción, pero las condiciones del factor de balance del hijo
        // pueden ser >= 0 o <= 0, no solo estrictamente > 0 o < 0.

        // Caso Izquierda-Izquierda (LL)
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Caso Izquierda-Derecha (LR)
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Caso Derecha-Derecha (RR)
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Caso Derecha-Izquierda (RL)
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Encuentra el nodo con el valor mínimo en un subárbol (para eliminación).
    private NodoAVL minValueNode(NodoAVL node) {
        NodoAVL current = node;
        // Recorre hacia la izquierda hasta encontrar el nodo más a la izquierda.
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Lista todos los pacientes en la cola de atención en orden (ID ascendente).
     * Esto se hace con un recorrido in-order.
     */
    public void listaPacientesEncolados() {
        if (root == null) {
            System.out.println("No hay pacientes en la cola de atención.");
            return;
        }
        System.out.println("--- Listado de Pacientes en Cola de Atención ---");
        inOrderTraversal(root);
        System.out.println("----------------------------------------------");
    }


    private void inOrderTraversal(NodoAVL node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.data);
            inOrderTraversal(node.right);
        }
    }
}
