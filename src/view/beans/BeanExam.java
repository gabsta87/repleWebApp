package view.beans;

import java.io.Serializable;

import javax.annotation.ManagedBean;

import control.Controller;
import control.Exam;

@ManagedBean
public class BeanExam extends BeanAbstractExercice implements Serializable{

	private static final long serialVersionUID = 8609768163232324368L;

	public BeanExam() {
		this.exercice = new Exam(Controller.getInstance().getSettings());
	}

	@Override
	public void init() {
		this.exercice = new Exam(Controller.getInstance().getSettings());
	}

}
