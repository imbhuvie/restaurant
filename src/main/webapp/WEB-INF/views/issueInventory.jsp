<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Item</title>
    <script src="https://cdn.tailwindcss.com"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body class="bg-gray-500 flex flex-col h-screen">
    <%@include file="navbar.jsp" %>

    <div class="flex justify-center w-full mx-auto p-10">
    <div class="bg-gray-400 p-8 rounded-lg shadow-lg w-96">
            <h2 class="text-2xl font-bold mb-6 text-center">Issue Stock From Inventory</h2>
        <c:if test="${not empty msg}" >
                 <p class="text-center text-s text-green-800 " >${msg}</p>
             </c:if>    <c:if test="${not empty error}" >
                                 <p class="text-center text-s text-red-800 " >${error}</p>
                             </c:if>

            <form action="issueInventory" method="post">
            <div class="mb-4">
                                <label for="item" class="block text-gray-700 font-semibold">Select Item</label>
                                <select id="item" name="item" required
                                    class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                                    <option selected disabled>select</option>
                                    <c:forEach items="${items}" var="item">
                                            <option value=${item.itemId} data-symbol="${item.unit.symbol}"  data-unit="${item.unit.unitName}">${item.itemName}(${item.itemId})</option>
                                    </c:forEach>
                                    </select>
                            </div>
            <div id="stockContainer" class="mb-4 hidden">
                <label for="availableStock" class="block text-gray-700 font-semibold">Available Stock</label>
                <input type="text" id="availableStock" name="availableStock" disabled
                    class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
            </div>


                <div class="mb-4">
                                    <label for="issuedStock" class="block text-gray-700 font-semibold">Issued Stock<span id="unitLabel"></span></label>
                                    <input type="text" id="issuedStock" name="issuedStock" required
                                        class="w-full m-1 px-3 py-1 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                 </div>


                <button type="submit" class="w-full bg-blue-500 text-white py-2 px-4 rounded-lg hover:bg-blue-600">Submit</button>
            </form>
        </div>

    </div>
        <script>

            var $jq = jQuery.noConflict();

            $jq(document).ready(function () {
                const unitLabel = document.getElementById('unitLabel');
                const itemSelect = document.getElementById('item');
                const stockContainer = document.getElementById('stockContainer');

                itemSelect.addEventListener('change', function () {
                    const itemId = this.value;

                    if (itemId) {
                        // Show stock container
                        stockContainer.classList.remove("hidden");

                        // Fetch stock using AJAX
                        $jq.ajax({
                            type: 'GET',
                            url: '/get-available-stock',
                            data: { id: itemId },
                            success: function (data) {
                                $jq("#availableStock").val(data);
                            },
                            error: function () {
                                alert("Error fetching stock");
                            }
                        });
                    }

                    // Update unit label
                    const selectedOption = this.options[this.selectedIndex];
                    const unit = selectedOption.getAttribute('data-unit');
                    unitLabel.textContent = '(' + unit + ')' || '--';
                });
            });



        </script>
</body>
</html>
