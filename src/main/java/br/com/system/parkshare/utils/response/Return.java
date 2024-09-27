package br.com.system.parkshare.utils.response;



public record Return(Object data, int page, int pageSize, int totalPages, long totalElements) {
}