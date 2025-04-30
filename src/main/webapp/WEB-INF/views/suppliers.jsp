<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Item</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-500 flex flex-col h-screen">
    <%@include file="navbar.jsp" %>

    <div class="flex justify-center w-full mx-auto p-10">
    <div class="bg-gray-400 p-8 rounded-lg shadow-lg">
        <c:if test="${not empty msg}" >
                 <p class="text-center text-s text-green-800 " >${msg}</p>
             </c:if>
             <c:if test="${not empty error}" >
                     <p class="text-center text-s text-red-800 " >${error}</p>
             </c:if>

            <div class="container w-full mx-auto">
                    <h2 class="text-2xl font-bold mb-4 text-center">Suppliers List</h2>
                    <c:if test="${not empty ifNotSupplier}" >
                                                      <p class="text-center font-semibold text-s text-red-800 " >${ifNotSupplier}</p>
                                              </c:if>
<c:if test="${not empty suppliers}" >
                    <table class="w-full border-collapse border border-gray-300 bg-white shadow-lg">
                        <thead>
                            <tr class="bg-blue-500 text-white">
                                <th class="border border-gray-300 px-4 py-2">S. No. </th>
                                <th class="border border-gray-300 px-4 py-2">Firm Name</th>
                                <th class="border border-gray-300 px-4 py-2">Firm Contact</th>
                                <th class="border border-gray-300 px-4 py-2">Contact Person Name</th>
                                <th class="border border-gray-300 px-4 py-2">Supplier Person Mobile</th>
                                <th class="border border-gray-300 px-4 py-2">Supplier Address</th>
                            </tr>
                        </thead>
                        <tbody>


                            <% int i=1; %>
                            <c:forEach var="supplier" items="${suppliers}">
                                <tr class="hover:bg-gray-100">
                                    <td class="border border-gray-300 px-4 py-2"><%= i++ %></td>
                                    <td class="border border-gray-300 px-4 py-2">${supplier.supplierName}</td>
                                    <td class="border border-gray-300 px-4 py-2">${supplier.supplierContact}</td>
                                    <td class="border border-gray-300 px-4 py-2">${supplier.supplierPersonName}</td>
                                    <td class="border border-gray-300 px-4 py-2">${supplier.supplierPersonContact}</td>
                                    <td class="border border-gray-300 px-4 py-2">${supplier.supplierAddress}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </c:if>
                </div>
        </div>

    </div>
</body>
</html>
