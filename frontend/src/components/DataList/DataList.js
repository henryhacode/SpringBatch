import { Button } from "@mui/material";
import { Container } from "@mui/system";
import { useState } from "react";
import axiosInstance from "../../utils/AxiosService";

export default function DataList(props) {
  const [status, setStatus] = useState("Ready");

  const runBatchClicked = async (e) => {
    setStatus("Requesting");
    console.log("Run batch");
    try {
      const ret = await axiosInstance.post("/batch");
      console.log(ret);
      setStatus(ret.data);
    } catch (err) {
      console.log(err);
      setStatus("Error")
    }
  }

  return (
    <Container sx={{mt: 5}}>
      <Button variant="contained" onClick={runBatchClicked} >Run Batch</Button>
      <div>Status: {status}</div>
    </Container>
  )
}
