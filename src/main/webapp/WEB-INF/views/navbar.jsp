<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="w-full bg-gray-50 text-blue-800 text-white p-4 shadow-md px-10">
    <div class="flex justify-between items-center">
        <!-- Logo -->
        <div class="text-4xl font-bold">
            <c:if test="${currentUser != null}">
                <a href="/home" class="text-blue-700 hover:text-blue-800">VYANJAN</a>
            </c:if>
            <c:if test="${currentUser == null}">
                <a href="/" class="text-blue-700 hover:text-blue-800">VYANJAN</a>
            </c:if>
        </div>

        <!-- Navigation Links -->
        <c:if test="${currentUser != null}">
            <div class="flex space-x-6 text-gray-900 font-semibold justify-center items-center">
                        <!-- Items -->
                <div class="relative inline-block text-left group">
                    <button type="button" class="inline-flex justify-center w-full px-6 py-2 text-lg text-gray-800 focus:outline-none">
                        Items
                    </button>
                      <!-- Dropdown menu -->
                    <div class="absolute right-0 mt-2 w-44 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:visible group-hover:opacity-100 transition-all duration-300">
                        <div class="py-1">
                              <a href="/add-item" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Add Item</a>
                              <a href="/update-item" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Update Item</a>
                              <a href="/delete-item" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Delete Item</a>
                              <a href="/items" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">View Items</a>
                        </div>
                    </div>
                </div>
                        <!-- Units -->
                <div class="relative inline-block text-left group">
                    <button type="button" class="inline-flex justify-center w-full px-6 py-2 text-lg text-gray-800 focus:outline-none">
                        Units
                    </button>
                      <!-- Dropdown menu -->
                    <div class="absolute right-0 mt-2 w-44 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:visible group-hover:opacity-100 transition-all duration-300">
                        <div class="py-1">
                              <a href="/add-unit" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Add Unit</a>
                              <a href="/update-unit" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Update Unit</a>
                              <a href="/delete-unit" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Delete Unit</a>
                              <a href="/units" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">View Unit</a>
                        </div>
                    </div>
                </div>
                        <!-- Suppliers -->
                <div class="relative  inline-block text-left group">
                    <button type="button" class="inline-flex justify-center w-full px-6 py-2 text-lg text-gray-800 focus:outline-none">
                        Suppliers
                    </button>
                      <!-- Dropdown menu -->
                    <div class="absolute right-0 mt-2 w-44 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:visible group-hover:opacity-100 transition-all duration-300">
                        <div class="py-1">
                              <a href="/add-supplier" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Add Supplier</a>
                              <a href="/update-supplier" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Update Supplier</a>
                              <a href="/delete-supplier" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Delete Supplier</a>
                              <a href="/suppliers" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">View Suppliers</a>
                        </div>
                    </div>
                </div>
                        <!-- Inventories -->
                <div class="relative  inline-block text-left group">
                    <button type="button" class="inline-flex justify-center w-full px-6 py-2 text-lg text-gray-800 focus:outline-none">
                        Inventories
                    </button>
                      <!-- Dropdown menu -->
                    <div class="absolute right-0 mt-2 w-44 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:visible group-hover:opacity-100 transition-all duration-300">
                        <div class="py-1">
                              <a href="/add-inventory" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Purchase Inventory</a>
                              <a href="/issue-inventory" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Issue Inventory</a>
                              <a href="/edit-inventory" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Edit Inventory</a>
                              <a href="/inventories" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">View Inventories</a>
                        </div>
                    </div>
                </div>





            </div>
        </c:if>

        <!-- Login/Register and Logout -->
        <div class="flex space-x-6 justify-center items-center text-gray-900">
            <c:if test="${currentUser == null}">
                <a href="/login" class="inline-flex justify-center w-full font-bold px-8 py-2 text-lg bg-blue-700 rounded text-gray-50">Login</a>
                <a href="/register" class="inline-flex justify-center w-full font-bold px-8 py-2 text-lg bg-blue-700 rounded text-gray-50">Register</a>
            </c:if>
            <c:if test="${currentUser != null}">
            <!-- Profile -->
                            <div class="relative  inline-block text-left group">
                                <button type="button" class="inline-flex justify-center w-full font-bold px-8 py-2 text-lg bg-blue-700 rounded text-gray-50 focus:outline-none">
                                    Profile
                                </button>
                                  <!-- Dropdown menu -->
                                <div class="absolute right-0 mt-2 w-44 rounded-md shadow-lg bg-white ring-1 ring-black ring-opacity-5 opacity-0 invisible group-hover:visible group-hover:opacity-100 transition-all duration-300">
                                    <div class="py-1">
                                          <a href="/profile" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">My Profile</a>
                                          <a href="/update-profile" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Update Profile</a>
                                          <a href="/logout" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Logout</a>
                                    </div>
                                </div>
                            </div>
            </c:if>
        </div>
    </div>
</nav>
