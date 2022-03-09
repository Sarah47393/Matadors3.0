<?php

namespace App\Controller;

use App\Entity\Dons;
use App\Form\DonsType;
use App\Repository\DonsRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

	
	  
class DonsController extends AbstractController
{
    /**
     * @Route("/index", name="dons_index", methods={"GET"})
     */
    public function index(DonsRepository $donsRepository): Response
    {
        return $this->render('dons/index.html.twig', [
            'dons' => $donsRepository->findAll(),
        ]);
    }
  
/**
     * @Route("/index1", name="dons_index1", methods={"GET"})
     */
    public function index1(DonsRepository $donsRepository): Response
    {
        return $this->render('dons/index1.html.twig', [
            'dons' => $donsRepository->findAll(),
        ]);
    }
    /**
     * @Route("/listd", name="dons_list", methods={"GET"})
     */
    public function listd(DonsRepository $donsRepository): Response
    {
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $dons = $donsRepository->findAll();
        
        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('dons/listd.html.twig', [
            'dons' => $dons,
        ]);
       // Load HTML to Dompdf
       $dompdf->loadHtml($html);
        
       // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
       $dompdf->setPaper('A4', 'portrait');

       // Render the HTML as PDF
       $dompdf->render();

       // Output the generated PDF to Browser (inline view)
       $dompdf->stream("mypdf.pdf", [
           "Attachment" => false
       ]);
   }
    /**
     * @Route("/new", name="dons_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $don = new Dons();
        $form = $this->createForm(DonsType::class, $don);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($don);
            $entityManager->flush();

            return $this->redirectToRoute('dons_new', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('dons/new.html.twig', [
            'don' => $don,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/dons/{id}", name="dons_show", methods={"GET"})
     */
    public function show(Dons $don): Response
    {
        return $this->render('dons/show.html.twig', [
            'don' => $don,
        ]);
    }
     
}