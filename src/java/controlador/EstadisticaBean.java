/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EstadisticaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Estadistica;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author CRISTOPHER
 */
@ManagedBean(name="estadisticaBean")
@ViewScoped
public class EstadisticaBean {
    private List<Estadistica> lista;
    private PieChartModel torta;
    private BarChartModel barra;
  public EstadisticaBean(){
      listar();
  }
  public void listar(){
      EstadisticaDAO dao;
      try{
          dao=new EstadisticaDAO();
      lista=dao.listarEstadistica();
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  public void graficar(){
      torta=new PieChartModel();
      for(Estadistica estadistica: lista){
          torta.set(estadistica.getEstado(),estadistica.getCantidad());
        }
        torta.setTitle("Estadisticas-Torta");
        torta.setLegendPosition("e");
        torta.setFill(true);
        torta.setShowDataLabels(true);
        torta.setDiameter(100);
        
        barra=new BarChartModel();
        
        for(int i = 0; i < lista.size();i++){
            ChartSeries serie=new BarChartSeries();
            serie.setLabel(lista.get(i).getEstado());
            serie.set(lista.get(i).getEstado(),lista.get(i).getCantidad());
            barra.addSeries(serie);
        }
        barra.setTitle("Estadisticas-Barra");
        barra.setLegendPosition("ne");
        Axis xAxis = barra.getAxis(AxisType.X);
        xAxis.setLabel("Estado");
        Axis yAxis = barra.getAxis(AxisType.Y);
        yAxis.setLabel("Cantidad");
        yAxis.setMin(0);
        yAxis.setMax(100);
    }

    public PieChartModel getTorta() {
        return torta;
    }
    public void setTorta(PieChartModel torta) {
        this.torta = torta;
    }
    public List<Estadistica> getLista() {
        return lista;
    }
    public void setLista(List<Estadistica> lista) {
        this.lista = lista;
    }
    public BarChartModel getBarra() {
        return barra;
    }
    public void setBarra(BarChartModel barra) {
        this.barra = barra;
    }
  
}
