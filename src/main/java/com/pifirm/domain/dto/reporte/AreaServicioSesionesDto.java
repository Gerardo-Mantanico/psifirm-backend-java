package com.pifirm.domain.dto.reporte;

public class AreaServicioSesionesDto {
    private Long areaId;
    private String areaNombre;
    private Long servicioId;
    private String servicioNombre;
    private Long sesiones;

    public AreaServicioSesionesDto(Long areaId, String areaNombre, Long servicioId, String servicioNombre, Long sesiones) {
        this.areaId = areaId;
        this.areaNombre = areaNombre;
        this.servicioId = servicioId;
        this.servicioNombre = servicioNombre;
        this.sesiones = sesiones;
    }

    public Long getAreaId() { return areaId; }
    public String getAreaNombre() { return areaNombre; }
    public Long getServicioId() { return servicioId; }
    public String getServicioNombre() { return servicioNombre; }
    public Long getSesiones() { return sesiones; }

    public void setAreaId(Long areaId) { this.areaId = areaId; }
    public void setAreaNombre(String areaNombre) { this.areaNombre = areaNombre; }
    public void setServicioId(Long servicioId) { this.servicioId = servicioId; }
    public void setServicioNombre(String servicioNombre) { this.servicioNombre = servicioNombre; }
    public void setSesiones(Long sesiones) { this.sesiones = sesiones; }
}