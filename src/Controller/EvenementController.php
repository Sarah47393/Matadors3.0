<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Form\EvenementType;
use App\Repository\EvenementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\File\File;
use Dompdf\Dompdf;
use Dompdf\Options;
use Swift_Mailer;
/**
 * @Route("/evenement")
 */
class EvenementController extends AbstractController
{
    /**
     * @Route("/", name="evenement_index", methods={"GET"})
     */
    public function index(EvenementRepository $evenementRepository): Response
    {
        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenementRepository->findAll(),
        ]);
    }
    /**
     * @Route("/ev", name="evenement_index1", methods={"GET"})
     */
    public function index1(EvenementRepository $evenementRepository): Response
    {
        return $this->render('evenement/index1.html.twig', [
            'evenements' => $evenementRepository->findAll(),
        ]);
    }    




     /**
     * @Route("/listevenement", name="evenement_pdf", methods={"GET"})
     */
   
  
        public function listpdf(EvenementRepository $EvenementRepository): Response
            {
        $pdfOptions = new Options();
            $pdfOptions->set('defaultFont', 'Arial');
            
            // Instantiate Dompdf with our options
            $dompdf = new Dompdf($pdfOptions);
            $evenement = $EvenementRepository->findAll();
               
            // Retrieve the HTML generated in our twig file
            $html = $this->renderView('evenement/listevenement.html.twig', [
                'evenements' => $evenement,
            ]);
            
            // Load HTML to Dompdf
            $dompdf->loadHtml($html);
            
            // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
            $dompdf->setPaper('A4', 'portrait');
    
            // Render the HTML as PDF
            $dompdf->render();
    
            // Output the generated PDF to Browser (force download)
            $dompdf->stream("mypdf.pdf", [
                "Attachment" => false
            ]);
       
        }

         /**
     * @Route("/TrierParNomDESCr", name="TrierParNomDESCr")
     */
    public function TrierParNomr(Request $request): Response
    {
        $repository = $this->getDoctrine()->getRepository(Evenement::class);
        $evenements = $repository->findByNamer();

        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenements,
        ]);
    }
    /**
     * @Route("/TrierParNomASCr", name="TrierParNomASCr")
     */
    public function TrierParNomdesr(Request $request): Response
    {
        $repository = $this->getDoctrine()->getRepository(Evenement::class);
        $evenements = $repository->findByNameascr();

        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenements,
        ]);
    }






    /**
     * @Route("/new", name="evenement_new", methods={"GET", "POST"})
     */
    public function new(Request $request): Response
    {
        $evenement = new evenement();
        $form = $this->createForm(evenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            
            $file = $form->get('QrCode')->getData();
            $fileName = md5(uniqid()).'.'.$file->guessExtension();
            try{
                $file->move(
                    $this->getParameter('uploads_directory'),
                    $fileName
                );
            }catch(FileException $e){

            }
            $entityManager = $this->getDoctrine()->getManager();
            $evenement->setQrCode($fileName);
            $entityManager->persist($evenement);
            $entityManager->flush();
           

  

            return $this->redirectToRoute('evenement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('evenement/new.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }
    

     

    /**
     * @Route("/{id}", name="evenement_show", methods={"GET"})
     */
    public function show(Evenement $evenement): Response
    {
        return $this->render('evenement/show.html.twig', [
            'evenement' => $evenement,
        ]);
    }
    

    /**
     * @Route("/maps/{id}", name="maps", methods={"GET"})
     */
    public function maps(Evenement $evenement): Response
    {
        return $this->render('evenement/maps.html.twig', [
            'evenement' => $evenement,
        ]);
    }



    /**
     * @Route("/{id}/edit", name="evenement_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Evenement $evenement, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EvenementType::class, $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            
                $file = $form->get('QrCode')->getData();
                $fileName = md5(uniqid()).'.'.$file->guessExtension();
                try{
                    $file->move(
                        $this->getParameter('uploads_directory'),
                        $fileName
                    );
                }catch(FileException $e){
    
                }
                $evenement->setQrCode($fileName);
            $entityManager->flush();

            return $this->redirectToRoute('evenement_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('evenement/edit.html.twig', [
            'evenement' => $evenement,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="evenement_delete", methods={"POST"})
     */
    public function delete(Request $request, Evenement $evenement, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$evenement->getId(), $request->request->get('_token'))) {
            $entityManager->remove($evenement);
            $entityManager->flush();
        }

        return $this->redirectToRoute('evenement_index', [], Response::HTTP_SEE_OTHER);
    }



    /**
     * @Route("/order1/{searchString}", name="order")
     */
    public function index3(EvenementRepository $evenementRepository, $searchString): Response
    {
        return $this->render('evenement/index.html.twig', [
            'evenements' => $evenementRepository->findByExampleField($searchString),
           
        ]);
    }

    public function mail( \Swift_Mailer $mailer)
    {
        $message = (new \Swift_Message('Vous Avez Ajouter un évenement!!'))
            ->setFrom('sarra.gara@esprit.tn')
            ->setTo('garasarra68@gmail.tn')
            ->setBody(
                $this->renderView(
                    // templates/emails/registration.html.twig
                    'emails/registration.html.twig',
                  
                ),
                'text/html'
                
            );
            $mailer->send($message);
            // you can remove the following code if you don't define a text version for your emails
            //->addPart(
                //$this->renderView(
                    // templates/emails/registration.txt.twig
                   // 'emails/registration.txt.twig',
                    
                //),
               // 'text/plain'
                
        //;
    
        $mailer->send($message);
            }
   




   
}
