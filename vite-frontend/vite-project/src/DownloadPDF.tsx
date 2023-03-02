import React, { useState } from 'react';
import axios, { AxiosResponse } from 'axios';

interface DownloadPDFProps {
  filename: string;
}

const DownloadPDF: React.FC<DownloadPDFProps> = ({ filename }) => {
  const [pdfData, setPdfData] = useState<string | null>(null);

  const handleDownloadClick = async () => {
    try {
      const response: AxiosResponse<Blob> = await axios.get(`http://localhost:8080/api/download`, {
        responseType: 'blob',
      });

      const pdfBlob = new Blob([response.data], {
        type: 'application/pdf',
      });

      const pdfUrl = URL.createObjectURL(pdfBlob);
      setPdfData(pdfUrl);

      // Open PDF in new window
      const newWindow = window.open(pdfUrl, '_blank');
      if (newWindow) {
        newWindow.focus();
      }
    } catch (error) {
      console.error('Error downloading PDF:', error);
    }
  };

  return (
    <div>
      <a href={pdfData || '#'} download={filename} rel="noopener noreferrer" onClick={handleDownloadClick}>
        <button> Download PDF </button>
      </a>
    </div>
  );
};

export default DownloadPDF;
