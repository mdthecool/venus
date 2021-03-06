package com.venus.controller.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import com.venus.model.Program;
import com.venus.model.Department;
import com.venus.model.impl.ProgramImpl;
import com.venus.dal.ProgramOperations;
import com.venus.dal.DepartmentOperations;
import com.venus.dal.DataAccessException;
import com.venus.dal.impl.ProgramOperationsImpl;
import com.venus.dal.impl.DepartmentOperationsImpl;
import com.venus.util.VenusSession;
import com.venus.util.VenusSessionFactory;

import com.venus.controller.request.ProgramRequest;

import org.springframework.stereotype.Service;
import com.venus.controller.response.error.ResponseException;

import org.apache.log4j.Logger;
import javax.ws.rs.core.Response;

@Service
public class ProgramService {
  private ProgramOperations pol = new ProgramOperationsImpl();
  private DepartmentOperations dol = new DepartmentOperationsImpl();
  private VenusSession vs = VenusSessionFactory.getVenusSession();
  private static final Logger log = Logger.getLogger(ProgramService.class);

  public Program getProgram(String name, String deptName) {
    Program program = null;
    Department dept = null;
    try {
      dept = dol.findDepartmentByName(deptName, null, vs);
    }
    catch (DataAccessException dae) {
      throw new RuntimeException("Error while getting dept: " + deptName, dae);
    }
    try {
      program = pol.findProgramByName(name, dept, vs);
    }
    catch (DataAccessException dae) {
      throw new RuntimeException("Error while getting program: " + name, dae);
    }
    return program;
  }
  
  public Program createUpdateProgram(ProgramRequest req) {
    String name = req.getName();
    String deptName = req.getDepartmentName();
    String code = req.getCode();
    String desc = req.getDescription();
    Integer duration = req.getDuration();
    String prerequisites = req.getPrerequisites();
    
    Department dept = null;
    Program program = null;

    try {
      dept = dol.findDepartmentByName(deptName, null, vs);
    }
    catch (DataAccessException dae) {
      throw new RuntimeException("Error while getting dept: " + deptName, dae);
    }
    if (dept == null) {
      throw new RuntimeException("No department with name:" + deptName);
    }

    try {
      program  = pol.createUpdateProgram(name, dept, code, desc, prerequisites, duration, null, null, vs);
    }
    catch (DataAccessException dae) {
      throw new RuntimeException("Error while creating/updating program: " + name, dae);
    }
    if (program == null) {
      throw new RuntimeException("Unable to create/update program");
    }
    return program;
  }

  public List<Program> getPrograms(String deptName, int offset, int maxRet) throws ResponseException {
    List<Program> programs = null;
    Department dept = null;

    try {
      dept = dol.findDepartmentByName(deptName, null, vs);
    }
    catch (DataAccessException dae) {
      String errStr = "Error while getting dept: " + deptName;
      log.error(errStr, dae);
      throw new ResponseException(Response.Status.INTERNAL_SERVER_ERROR, null, errStr);
    }
    if (dept == null) {
      throw new ResponseException(Response.Status.NOT_FOUND, null, "Cannot find department: " + deptName);
    }

    try {
      programs = pol.getPrograms(dept, offset, maxRet, vs);
    }
    catch (DataAccessException dae) {
      String errStr = "Error while getting programs for: " + deptName;
      log.error(errStr, dae);
      throw new ResponseException(Response.Status.INTERNAL_SERVER_ERROR, null, errStr);
    }
    
    return programs;
  }

  public List getPrograms(int offset, int maxRet) {
    List<Department> depts = null;

    try {
      depts = dol.getDepartments(offset, maxRet, null, vs);
    }
    catch (DataAccessException dae) {
      throw new RuntimeException("Error while getting depts", dae);
    }
    if (depts == null) {
      return null;
    }

    for (Department d: depts) {
      try {
	List<Program> programs = pol.getPrograms(d, offset, maxRet, vs);
	d.setPrograms(programs);
      }
      catch (DataAccessException dae) {
	throw new RuntimeException("Error while getting programs for dept: " + d.getName(), dae);
      }
    }
    return depts;
  }

}
